package com.stock.admin.service;

import static com.stock.admin.utils.Helper.pageRequestFor;
import static com.stock.admin.utils.StockAdminConstants.PRODUCT_DOES_NOT_EXISTS;
import static com.stock.admin.utils.StockAdminConstants.PATH_PRODUCT_NAME;
import static com.stock.admin.utils.StockAdminConstants.PATH_PRODUCT_PACKAGING;
import static com.stock.admin.utils.StockAdminConstants.STOCK_DATE;
import static com.stock.admin.utils.StockAdminConstants.TOTAL_STOCK;

import com.mongodb.client.result.UpdateResult;
import com.stock.admin.exception.StockAdminApplicationException;
import com.stock.admin.model.entity.Product;
import com.stock.admin.model.entity.Stock;
import com.stock.admin.model.request.StockRequest;
import com.stock.admin.repository.StocksRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Stock service.
 */
@Service
public class StockService {
    private final StocksRepository stocksRepository;
    private final ProductService productService;
    private final MongoTemplate mongoTemplate;

    /**
     * Instantiates a new Stock service.
     *
     * @param stocksRepository the stocks repository
     */
    @Autowired
    public StockService(StocksRepository stocksRepository, ProductService productService, MongoTemplate mongoTemplate) {
        this.stocksRepository = stocksRepository;
        this.productService = productService;
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Create or Update stocks.
     *
     * @param stockRequest the stock
     * @return the stocks
     */
    @Transactional
    public Stock createOrUpdate(StockRequest stockRequest) {
        return productService
                .getByProductNameAndPackaging(stockRequest.getProductName(), stockRequest.getPackaging())
                .map(product -> {
                    Optional<Stock> stockFound = findStock(stockRequest);
                    Stock updatedStock = stockFound
                            .map(stock -> handleWhenStockFound(stock, stockRequest))
                            .orElseGet(() -> handleWhenStockNotFound(stockRequest, product));
                    cascadeUpdate(stockRequest);
                    return updatedStock;
                }).orElseThrow(() -> new StockAdminApplicationException(PRODUCT_DOES_NOT_EXISTS, HttpStatus.NOT_FOUND));
    }

    private Optional<Stock> findStock(StockRequest stockRequest) {
        // Query to check stock exist for given date
        Query queryToCheckStockForGivenDate = new Query();
        Criteria criteria = matchingProductCriteria(stockRequest)
                .and(STOCK_DATE).is(stockRequest.getStockDate());
        queryToCheckStockForGivenDate.addCriteria(criteria);
        return Optional.ofNullable(mongoTemplate.findOne(queryToCheckStockForGivenDate, Stock.class));
    }

    private Criteria matchingProductCriteria(StockRequest stockRequest) {
        return Criteria.where(PATH_PRODUCT_NAME).is(stockRequest.getProductName())
                .and(PATH_PRODUCT_PACKAGING).is(stockRequest.getPackaging());
    }

    private Stock handleWhenStockFound(Stock stockFound, StockRequest stockRequest) {
        // Already stock found for given date

        // Update the found stockFound
        stockFound.setOpeningStock(stockFound.getOpeningStock() + stockRequest.getOpeningStock());
        stockFound.setClosingStock(stockFound.getClosingStock() - stockRequest.getClosingStock());

        stockFound.setTotalStock(stockFound.getTotalStock() + stockRequest.getOpeningStock() - stockRequest.getClosingStock());
        return stocksRepository.save(stockFound);
    }

    private Stock handleWhenStockNotFound(StockRequest stockRequest, Product product) {
        // No stock found for given date
        // Query to check stock exist for previous dates
        Optional<Stock> previousStockAvailable = findPreviousStocks(stockRequest);
        return stocksRepository.save(createStockWithTotalStock(stockRequest, product, previousStockAvailable.map(Stock::getTotalStock)));
    }

    private Optional<Stock> findPreviousStocks(StockRequest stockRequest) {
        Query queryToCheckStockForPreviousDates = new Query();
        Criteria criteria = matchingProductCriteria(stockRequest).and(STOCK_DATE).lt(stockRequest.getStockDate());
        queryToCheckStockForPreviousDates
                .addCriteria(criteria)
                .with(Sort.by(Sort.Direction.DESC, STOCK_DATE))
                .limit(1);
        return Optional.ofNullable(mongoTemplate.findOne(queryToCheckStockForPreviousDates, Stock.class));
    }

    private Stock createStockWithTotalStock(StockRequest stockRequest, Product product, Optional<Integer> totalStock) {
        Stock stock = new Stock();
        stock.setProduct(product);
        stock.setStockDate(stockRequest.getStockDate());
        stock.setOpeningStock(stockRequest.getOpeningStock());
        stock.setClosingStock(stockRequest.getClosingStock());
        if (totalStock.isPresent()) {
            // Stock found for previous date, get the totalStock count foreword, and create new stock
            stock.setTotalStock(totalStock.get() + stockRequest.getOpeningStock() - stockRequest.getClosingStock());
        } else {
            // No stock found for previous dates as well, create new stock
            stock.setTotalStock(stockRequest.getOpeningStock() - stockRequest.getClosingStock());
        }
        return stock;
    }

    public void cascadeUpdate(StockRequest stockRequest) {

        // Query to check for stocks present on later dates, so that cascade the update for all the stocks
        Query queryToCheckStocksOnLaterDate = new Query();
        Criteria criteria = matchingProductCriteria(stockRequest).and(STOCK_DATE).gt(stockRequest.getStockDate());
        queryToCheckStocksOnLaterDate.addCriteria(criteria);

        Update updateStock = new Update();
        // Increment the total stock
        updateStock.inc(TOTAL_STOCK, stockRequest.getOpeningStock() - stockRequest.getClosingStock());

        // Update all records matching query with total stock
        UpdateResult updateResult = mongoTemplate.updateMulti(queryToCheckStocksOnLaterDate, updateStock, Stock.class);

        System.out.println("Updates Matched to: " + updateResult.getMatchedCount());
        System.out.println("Updates Done to: " + updateResult.getModifiedCount());
    }

    /**
     * Gets all.
     *
     * @return the all
     * @param pageNum
     * @param size
     * @param sortType
     */
    public Page<Stock> getAll(int pageNum, int size, String sortType) {
        return stocksRepository.findAll(pageRequestFor(pageNum, size, sortType, STOCK_DATE));
    }
}



