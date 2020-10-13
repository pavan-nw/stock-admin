package com.stock.admin.repository;

import com.stock.admin.model.entity.Stock;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The interface Shops repository.
 */
public interface StocksRepository extends MongoRepository<Stock, String> {

    /**
     * Find by Id
     * @param id id of the record
     * @return
     */
    Optional<Stock> findById(String id);

    Page<Stock> findByStockDateLessThanEqual(Date stockDate, Pageable pageable);

    Page<Stock> findByStockDateBefore(Date stockDate, Pageable pageable);

    Page<Stock> findByStockDateAfter(Date stockDate, Pageable pageable);
}
