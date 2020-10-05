package com.stock.admin.repository;

import com.stock.admin.model.entity.Product;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Products repository.
 */
@Repository
public interface ProductsRepository extends MongoRepository<Product, String> {

    /**
     * Find by name product.
     *
     * @param productName the product name
     * @return the product
     */
    Product findByName(String productName);

    /**
     * Find by code product.
     *
     * @param productCode the product code
     * @return the product
     */
    Product findByCode(String productCode);


    Optional<Product> findByNameAndPackaging(String name, String packaging);

    /**
     * Find by shop code list.
     *
     * @param shopCode the shop code
     * @return the list
     */
    List<Product> findByShopCode(String shopCode);
}
