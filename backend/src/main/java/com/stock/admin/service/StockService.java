package com.stock.admin.service;

import com.stock.admin.exception.StockAdminApplicationException;
import com.stock.admin.model.entity.Stock;
import com.stock.admin.model.request.StockRequest;
import com.stock.admin.repository.StocksRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
    public Stock createOrUpdate(StockRequest stockRequest) {

        return productService
                .getByProductNameAndPackaging(stockRequest.getProductName(), stockRequest.getPackaging())
                .map((product -> {
            Stock stock = new Stock();
            stock.setProduct(product);
            stock.setOpeningStock(stockRequest.getOpeningStock());
            stock.setClosingStock(stockRequest.getClosingStock());
            stock.setStockDate(stockRequest.getStockDate());
            stock.setTotalStock(stockRequest.getOpeningStock()-stockRequest.getClosingStock());
            return stocksRepository.save(stock);
        })).orElseThrow(()->new StockAdminApplicationException("Product does not exists", HttpStatus.NOT_FOUND));
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<Stock> getAll() {
        return stocksRepository.findAll();
    }
}



