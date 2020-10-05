package com.stock.admin.service;

import com.stock.admin.exception.StockAdminApplicationException;
import com.stock.admin.model.entity.Product;
import com.stock.admin.model.response.ErrorResponse;
import com.stock.admin.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Product service.
 */
@Service
public class ProductService {
    private final ProductsRepository productsRepository;

    /**
     * Instantiates a new Product service.
     *
     * @param productsRepository the products repository
     */
    @Autowired
    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    /**
     * Create product.
     *
     * @param product the create product request
     * @return the product
     */
    public Product create(Product product) {
        return productsRepository.save(product);
    }

    /**
     * Gets all.
     *
     * @return the all
     */
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

    public Optional<Product> getByProductNameAndPackaging(String productName, String packaging) {
        return productsRepository.findByNameAndPackaging(productName, packaging);
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
    public Product updateByProductCode(String productCode, Product newProduct) {
        Product product = productsRepository.findByCode(productCode);
        if (product != null) {
            product.setName(newProduct.getName());
            product.setPackaging(newProduct.getPackaging());
            return productsRepository.save(product);
        }
        throw new StockAdminApplicationException("Product does not exists", HttpStatus.NOT_FOUND);
    }

    /**
     * Delete.
     *
     * @param productCode the prod Code
     * @return the product
     */
    public Product deleteByProductCode(String productCode) {
        Product product = productsRepository.findByCode(productCode);
        if (product != null) {
            productsRepository.delete(product);
            return product;
        }
        throw new StockAdminApplicationException("Product does not exists", HttpStatus.NOT_FOUND);
    }
}
