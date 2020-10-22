package com.stock.admin.repository;

import com.stock.admin.model.entity.Stock;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * The interface Shops repository.
 */
public interface StocksRepository extends MongoRepository<Stock, String> {

	/**
	 * Find by Id
	 *
	 * @param id id of the record
	 * @return
	 */
	Optional<Stock> findById(String id);

	/**
	 * Find by stock date less than equal page.
	 *
	 * @param stockDate the stock date
	 * @param pageable  the pageable
	 * @return the page
	 */
	Page<Stock> findByStockDateLessThanEqual(Date stockDate, Pageable pageable);

	/**
	 * Find by stock date before page.
	 *
	 * @param stockDate the stock date
	 * @param pageable  the pageable
	 * @return the page
	 */
	Page<Stock> findByStockDateBefore(Date stockDate, Pageable pageable);

	/**
	 * Find by stock date after page.
	 *
	 * @param stockDate the stock date
	 * @param pageable  the pageable
	 * @return the page
	 */
	Page<Stock> findByStockDateAfter(Date stockDate, Pageable pageable);

	/**
	 * Find by stock date between.
	 *
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @return the page
	 */
	@Query(value = "{ 'stockDate' : {$gte : ?0, $lte: ?1 }}")
	Page<Stock> findByStockDateBetween(Date fromDate, Date toDate,Pageable pageable);
}
