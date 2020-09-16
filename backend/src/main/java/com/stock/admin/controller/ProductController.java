package com.stock.admin.controller;

import com.stock.admin.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.stock.admin.service.ProductService;

import java.awt.*;
import java.util.List;



@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET,produces = "application/json")
    public List<Product> getAll(){
        List<Product> products=productService.getAll();
        return products;
    }

    @RequestMapping(method = RequestMethod.POST,produces = "application/json")
    public Product addProduct(@RequestBody Product newProduct) {
        Product addedProduct=productService.create(newProduct.getCode(),newProduct.getName(),newProduct.getShopCode(),newProduct.getCreatedAt(),newProduct.getUpatedAt());
        return addedProduct;
    }


}