package com.stock.admin.service;

import com.stock.admin.model.entity.Product;
import com.stock.admin.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
     * @param product the create product request
     * @return the product
     */
//create operation
    public Product create(Product product) {
        return productsRepository.save(product);
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
     * Gets by Product code.
     *
     * @param productCode the Product code
     * @return the by Product code
     */
    public Optional<Product> getByProductCode(String productCode) {
        return Optional.ofNullable(productsRepository.findByCode(productCode));

    }


    /**
     * Gets all products by shop id.
     *
     * @param shopCode the shop code
     * @return the all products by shop id
     */
    public List<Product> getAllProductsByShopId(String shopCode) {
        return productsRepository.findByShopCode(shopCode);
    }


    /**
     * Update by Product code product.
     *
     * @param productCode the prod code
     * @param newProduct  the new product
     * @return the product
     */
//Update operation
    public Product updateByProductCode(String productCode, Product newProduct) {
        Product product = productsRepository.findByCode(productCode);
        product.setName(newProduct.getName());
        product.setPackaging(newProduct.getPackaging());
        return productsRepository.save(product);

    }

    /**
     * Delete.
     *
     * @param productCode the prod Code
     */
//Delete operation
    public void deleteByProductCode(String productCode) {
        Product product = productsRepository.findByCode(productCode);
        productsRepository.delete(product);

    }
}
