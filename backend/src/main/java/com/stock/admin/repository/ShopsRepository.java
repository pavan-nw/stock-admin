package com.stock.admin.repository;

import com.stock.admin.model.entity.Shop;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The interface Shops repository.
 */
public interface ShopsRepository extends MongoRepository<Shop, String> {

    /**
     * Find by name shop.
     *
     * @param shopName the shop name
     * @return the shop
     */
    Shop findByName(String shopName);

    /**
     * Find by shop code shop.
     *
     * @param shopCode the shop code
     * @return the shop
     */
    Shop findByShopCode(String shopCode);
}
