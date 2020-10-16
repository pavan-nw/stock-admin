package com.stock.admin.service;

import com.stock.admin.exception.StockAdminApplicationException;
import com.stock.admin.model.entity.Shop;
import com.stock.admin.repository.ShopsRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * The type Shop service.
 */
@Service
public class ShopService {
    private final ShopsRepository shopsRepository;

    /**
     * Instantiates a new Shop service.
     *
     * @param shopsRepository the shops repository
     */
    @Autowired
    public ShopService(ShopsRepository shopsRepository) {
        this.shopsRepository = shopsRepository;
    }

    /**
     * Create shop.
     *
     * @param shop the shop
     * @return the shop
     */
    public Shop create(Shop shop) {
        return shopsRepository.save(shop);
    }

    /**
     * Gets all.
     *
     * @return the all
     */
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
    public Optional<Shop> getByShopCode(String shopcode) {
        return Optional.ofNullable(shopsRepository.findByShopCode(shopcode));
    }


    /**
     * Update by shop code shop.
     *
     * @param shopCode the shop code
     * @param newShop  the new shop
     * @return the shop
     */
    public Shop updateByShopCode(String shopCode, Shop newShop) {
        Shop shop = shopsRepository.findByShopCode(shopCode);
        if (shop != null) {
            shop.setName(newShop.getName());
            shop.setLocation(newShop.getLocation());
            return shopsRepository.save(shop);
        }
        throw new StockAdminApplicationException("Shop does not exists", HttpStatus.NOT_FOUND);
    }

    /**
     * Delete.
     *
     * @param shopCode the shop code
     * @return the shop
     */
    public Shop delete(String shopCode) {

        Shop shop = shopsRepository.findByShopCode(shopCode);
        if (shop != null) {
            shopsRepository.delete(shop);
            return shop;
        }
        throw new StockAdminApplicationException("Shop does not exists", HttpStatus.NOT_FOUND);
    }

}



