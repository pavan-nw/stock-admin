package com.stock.admin.service;

import com.stock.admin.model.Product;
import com.stock.admin.model.Shop;
import com.stock.admin.repository.ShopsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ShopService {

    @Autowired
    private ShopsRepository shopsRepository;

    //create operation
    public Shop create(String shopCode, String shopName, String shopLocation){
        return shopsRepository.save(new Shop(shopCode, shopName, shopLocation));
    }

    //Retrieve Operation
    public List<Shop> getAll(){
        return shopsRepository.findAll();
    }

    public Shop getByName(String shopName){
        return shopsRepository.findByName(shopName);
    }

    public Shop getByShopCode(String shopcode){
        return shopsRepository.findByCode(shopcode);
    }

    //Update operation
    public Shop update(String shopCode,String shopName, String location){
        Shop shop=shopsRepository.findByCode(shopCode);
        shop.setName(shopName);
        shop.setLocation(location);
        return shopsRepository.save(shop);
    }

    //Delete operation
    public void delete(String shopName){
        Shop shop=shopsRepository.findByName(shopName);
        shopsRepository.delete(shop);

    }
}



