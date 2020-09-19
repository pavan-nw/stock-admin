package com.stock.admin.service;

import com.stock.admin.model.entity.Shop;
import com.stock.admin.repository.ShopsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * The type Shop service.
 */
@Service
public class ShopService {

    @Autowired
    private ShopsRepository shopsRepository;

    /**
     * Create shop.
     *
     * @param shopCode     the shop code
     * @param shopName     the shop name
     * @param shopLocation the shop location
     * @return the shop
     */
//create operation
    public Shop create(String shopCode, String shopName, String shopLocation) {
        return shopsRepository.save(new Shop(shopCode, shopName, shopLocation));
    }

    /**
     * Gets all.
     *
     * @return the all
     */
//Retrieve Operation
    public List<Shop> getAll() {
        return shopsRepository.findAll();
    }

    /**
     * Gets by name.
     *
     * @param shopName the shop name
     * @return the by name
     */
    public Shop getByName(String shopName) {
        return shopsRepository.findByName(shopName);
    }

    /**
     * Gets by shop code.
     *
     * @param shopcode the shopcode
     * @return the by shop code
     */
    public Shop getByShopCode(String shopcode) {
        return shopsRepository.findByCode(shopcode);
    }

    /**
     * Update shop.
     *
     * @param shopCode the shop code
     * @param shopName the shop name
     * @param location the location
     * @return the shop
     */
//Update operation
    public Shop update(String shopCode, String shopName, String location) {
        Shop shop = shopsRepository.findByCode(shopCode);
        shop.setName(shopName);
        shop.setLocation(location);
        return shopsRepository.save(shop);
    }

    /**
     * Delete.
     *
     * @param shopName the shop name
     */
//Delete operation
    public void delete(String shopName) {
        Shop shop = shopsRepository.findByName(shopName);
        shopsRepository.delete(shop);

    }
}



