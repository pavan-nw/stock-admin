package com.stock.admin.repository;

import com.stock.admin.model.entity.Stock;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The interface Shops repository.
 */
public interface StocksRepository extends MongoRepository<Stock, String> {

    /**
     * Find by stock date less than equal and product shop code page.
     *
     * @param stockDate the stock date
     * @param shopCode  the shop code
     * @param pageable  the pageable
     * @return the page
     */
    Page<Stock> findByStockDateLessThanEqualAndProduct_ShopCode(Date stockDate, String shopCode, Pageable pageable);

    /**
     * Find by product shop code page.
     *
     * @param shopCode the shop code
     * @param pageable the pageable
     * @return the page
     */
    Page<Stock> findByProduct_ShopCode(String shopCode, Pageable pageable);

    /**
     * Find by stock date less than equal and product shop code page.
     *
     * @param stockDate the stock date
     * @param shopCode  the shop code
     * @param productName the product name
     * @param packaging the packaging
     * @param pageable  the pageable
     * @return the page
     */
    Page<Stock> findByStockDateLessThanEqualAndProduct_ShopCodeAndProduct_NameAndProduct_Packaging(Date stockDate, String shopCode,
                                                                                String productName, String packaging,
                                                                                Pageable pageable);
    
    /**
     * Find by stock date less than equal and product shop code and product name.
     *
     * @param stockDate the stock date
     * @param shopCode the shop code
     * @param productName the product name
     * @param pageable the pageable
     * @return the page
     */
    Page<Stock> findByStockDateLessThanEqualAndProduct_ShopCodeAndProduct_Name(Date stockDate, String shopCode,
            String productName,
            Pageable pageable);
    
    /**
     * Find by stock date less than equal and product shop code and product packaging.
     *
     * @param stockDate the stock date
     * @param shopCode the shop code
     * @param packaging the packaging
     * @param pageable the pageable
     * @return the page
     */
    Page<Stock> findByStockDateLessThanEqualAndProduct_ShopCodeAndProduct_Packaging(Date stockDate, String shopCode,
            String packaging,
            Pageable pageable);
    
    /**
     * Find by stock date less than equal page.
     *
     * @param stockDate the stock date
     * @param pageable  the pageable
     * @return the page
     */
    Page<Stock> findByStockDateLessThanEqual(Date stockDate, Pageable pageable);
}
