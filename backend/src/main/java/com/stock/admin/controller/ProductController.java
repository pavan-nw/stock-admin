package com.stock.admin.controller;

import com.stock.admin.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.stock.admin.service.ProductService;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/create")
    public String create(@RequestParam String productCode,@RequestParam String productName,@RequestParam String shopCode){
        Product product=productService.create(productCode,productName,shopCode);
        return product.toString();
    }
    @RequestMapping("/get")
    public Product getProduct(@RequestParam String productName) {
        return productService.getByName(productName);
    }

    @RequestMapping("/getAll")
    public List<Product> getAllProducts(){
        return productService.getAll();
    }

    @RequestMapping("/update")
    public String updateProduct(@RequestParam String productCode,@RequestParam String productName,@RequestParam String shopCode){
       return productService.update(productCode,productName,shopCode).toString();
    }

    @RequestMapping("/delete")
    public String deleteProduct(@RequestParam String productName) {
         productService.delete(productName);
         return "Deleted"+productName;
    }
}