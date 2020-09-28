package com.stock.admin.controller;


import com.stock.admin.model.entity.Shop;
import com.stock.admin.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Shop controller.
 */
@RestController
@RequestMapping("/shops")
public class ShopController {
    @Autowired
    private ShopService shopService;

    /**
     * Gets all shops.
     *
     * @return the all shops
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<Shop> getAllShops() {
        List<Shop> shops = shopService.getAll();
        return shops;
    }

    /**
     * Gets shop by id.
     *
     * @param shopCode the shop code
     * @return the shop by id
     */
    @GetMapping(value = "/{shopCode}", produces = "application/json; charset=UTF-8")
    public Shop getShopById(@PathVariable("shopCode") String shopCode) {
        return shopService.getByShopCode(shopCode);
    }


    /**
     * Add shop shop.
     *
     * @param newShop the new shop
     * @return the shop
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Shop addShop(@RequestBody Shop newShop) {
        Shop addedShop = shopService.create(newShop);
        return addedShop;
    }

}
