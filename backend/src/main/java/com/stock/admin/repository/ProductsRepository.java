package com.stock.admin.repository;

import com.stock.admin.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends MongoRepository<Product,String> {

        public Product findByName(String productName);
        public Product findByCode(String productCode);
        public List<Product> findByShopCode(String shopCode);
}
