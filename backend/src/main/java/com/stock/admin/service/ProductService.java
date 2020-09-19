package com.stock.admin.service;

import com.stock.admin.model.entity.Product;
import com.stock.admin.model.request.ProductRequest;
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
     * @param productRequest the create product request
     * @return the product
     */
//create operation
    public Product create(ProductRequest productRequest) {
        return productsRepository.save(productRequest.getProduct());
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
     * Gets by product id.
     *
     * @param productId the product id
     * @return the by product id
     */
    public Product getByProductId(String productId) {
        return productsRepository.findByCode(productId);
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
     * Update by product id product.
     *
     * @param prodCode   the prod code
     * @param newProduct the new product
     * @return the product
     */
//Update operation
    public Product updateByProductId(String prodCode, Product newProduct) {
        Product product = productsRepository.findByCode(prodCode);
        product.setName(newProduct.getName());
        product.setPackaging(newProduct.getPackaging());
        return productsRepository.save(product);

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
