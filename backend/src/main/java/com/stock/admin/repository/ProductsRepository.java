package com.stock.admin.repository;

import com.stock.admin.model.entity.Product;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

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


    /**
     * Find by name and packaging optional.
     *
     * @param name      the name
     * @param packaging the packaging
     * @return the optional
     */
    Optional<Product> findByNameAndPackagingAndShopCode(String name, String packaging,String shopCode);

    /**
     * Find by shop code list.
     *
     * @param shopCode the shop code
     * @param pageable the pageable
     * @return the list
     */
    Page<Product> findByShopCode(String shopCode, Pageable pageable);
}
