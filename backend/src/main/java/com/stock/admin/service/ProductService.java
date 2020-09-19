package com.stock.admin.service;

import com.stock.admin.model.entity.Product;
import com.stock.admin.model.request.CreateProductRequest;
import com.stock.admin.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Product service.
 */
@Service
public class ProductService {
    @Autowired
    private ProductsRepository productsRepository;


    /**
     * Create product.
     *
     * @param createProductRequest the create product request
     * @return the product
     */
//create operation
    public Product create(CreateProductRequest createProductRequest) {
        return productsRepository.save(createProductRequest.getProduct());
    }

    /**
     * Gets all.
     *
     * @return the all
     */
//Retrieve Operation
    public List<Product> getAll() {
        return productsRepository.findAll();
    }

    /**
     * Gets by name.
     *
     * @param productName the product name
     * @return the by name
     */
    public Product getByName(String productName) {
        return productsRepository.findByName(productName);
    }

    /**
     * Update product.
     *
     * @param prodCode the prod code
     * @param prodName the prod name
     * @param shopCode the shop code
     * @return the product
     */
//Update operation
    public Product update(String prodCode, String prodName, String shopCode) {
        Product prod = productsRepository.findByCode(prodCode);
        prod.setName(prodName);
        prod.setShopCode(shopCode);
        return productsRepository.save(prod);
    }

    /**
     * Delete.
     *
     * @param prodName the prod name
     */
//Delete operation
    public void delete(String prodName) {
        Product prod = productsRepository.findByName(prodName);
        productsRepository.delete(prod);

    }
}
