package com.stock.admin.controller;

import com.stock.admin.model.CreateProductRequest;
import com.stock.admin.model.Product;
import com.stock.admin.model.ProductResponse;
import com.stock.admin.model.Response;
import com.stock.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * The type Product controller.
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * Gets all products.
     *
     * @return the all products
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<Product> getAllProducts() {
        List<Product> products = productService.getAll();
        return products;
    }


    @RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ProductResponse addProduct(@RequestBody CreateProductRequest createProductRequest) {
        Product addedProduct = productService.create(createProductRequest);
        ProductResponse productResponseBody=new ProductResponse();
        productResponseBody.setPayload(addedProduct);
        productResponseBody.setStatus(Response.ResponseStatus.SUCCESS);
        return productResponseBody;
    }


}