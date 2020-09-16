package com.stock.admin.controller;


import com.stock.admin.model.Product;
import com.stock.admin.model.Shop;
import com.stock.admin.service.ProductService;
import com.stock.admin.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @RequestMapping(method = RequestMethod.GET,produces = "application/json")
    public List<Shop> getAllShops(){
        List<Shop> shops=shopService.getAll();
        return shops;
    }

    @GetMapping(value = "/{shopCode}", produces = "application/json")
    public Shop getShopById(@PathVariable("shopCode") String shopCode) {
        return shopService.getByShopCode (shopCode);
    }


    @RequestMapping(method = RequestMethod.POST,produces = "application/json")
    public Shop addShop(@RequestBody Shop newShop){
        Shop addedShop=shopService.create(newShop.getCode(),newShop.getName(),newShop.getLocation());
        return addedShop;
    }

}
