package com.stock.admin.repository;

import com.stock.admin.model.Product;
import com.stock.admin.model.Shop;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShopsRepository extends MongoRepository<Shop,String> {

    public Shop findByName(String shopName);
    public Shop findByCode(String shopCode);

}
