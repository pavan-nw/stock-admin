package com.stock.admin.service;

import static com.stock.admin.utils.Helper.pageRequestFor;
import static com.stock.admin.utils.StockAdminConstants.PATH_PRODUCT_NAME;
import static com.stock.admin.utils.StockAdminConstants.PATH_PRODUCT_PACKAGING;
import static com.stock.admin.utils.StockAdminConstants.PATH_PRODUCT_SHOP_CODE;
import static com.stock.admin.utils.StockAdminConstants.PRODUCT_DOES_NOT_EXISTS;
import static com.stock.admin.utils.StockAdminConstants.STOCK_DATE;
import static com.stock.admin.utils.StockAdminConstants.TOTAL_STOCK;

import com.mongodb.client.result.UpdateResult;
import com.stock.admin.exception.StockAdminApplicationException;
import com.stock.admin.model.entity.Product;
import com.stock.admin.model.entity.Stock;
import com.stock.admin.model.request.StockRequest;
import com.stock.admin.repository.StocksRepository;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
     * @param productService   the product service
     * @param mongoTemplate    the mongo template
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
		return productService.getByProductNameAndPackagingAndShopCode(stockRequest.getProductName(),
				stockRequest.getPackaging(), stockRequest.getShopCode()).map(product -> {
					Optional<Stock> stockFound = findStock(stockRequest);
					Stock updatedStock = stockFound.map(stock -> handleWhenStockFound(stock, stockRequest))
							.orElseGet(() -> handleWhenStockNotFound(stockRequest, product));
					cascadeUpdate(stockRequest);
					return updatedStock;
				}).orElseThrow(() -> new StockAdminApplicationException(PRODUCT_DOES_NOT_EXISTS, HttpStatus.NOT_FOUND));
	}
	
	

	/**
	 * Find stock.
	 *
	 * @param stockRequest the stock request
	 * @return the optional
	 */
	private Optional<Stock> findStock(StockRequest stockRequest) {
		// Query to check stock exist for given date
		Query queryToCheckStockForGivenDate = new Query();
		Criteria criteria = matchingProductCriteria(stockRequest).and(STOCK_DATE).is(stockRequest.getStockDate());
		queryToCheckStockForGivenDate.addCriteria(criteria);
		return Optional.ofNullable(mongoTemplate.findOne(queryToCheckStockForGivenDate, Stock.class));
	}

	/**
	 * Matching product criteria.
	 *
	 * @param stockRequest the stock request
	 * @return the criteria
	 */
	private Criteria matchingProductCriteria(StockRequest stockRequest) {
		return Criteria.where(PATH_PRODUCT_NAME).is(stockRequest.getProductName()).and(PATH_PRODUCT_PACKAGING)
				.is(stockRequest.getPackaging()).and(PATH_PRODUCT_SHOP_CODE).is(stockRequest.getShopCode());
	}

       /**
     * Cascade update.
     *
     * @param stockRequest the stock request
     */
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
     * @param pageNum  the page num
     * @param size     the size
     * @param sortType the sort type
     * @return the all
     */
    public Page<Stock> getAll(int pageNum, int size, String sortType) {
        return stocksRepository.findAll(pageRequestFor(pageNum, size, sortType, STOCK_DATE));
    }
    
    /**
     * Find by stock date between.
     *
     * @param fromDate the from date
     * @param toDate the to date
     * @param pageRequest the page request
     * @return the page
     */
    public Page<Stock> findByProductShopCodeAndStockDateBetween(String shopCode, Date fromDate, Date toDate ,Pageable pageRequest) {
    	Pageable wholePage = Pageable.unpaged();
        return stocksRepository.findByProduct_ShopCodeAndStockDateBetween(shopCode,fromDate, toDate, wholePage);
    }
    


	/**
	 * Handle when stock found.
	 *
	 * @param stockFound the stock found
	 * @param stockRequest the stock request
	 * @return the stock
	 */
	private Stock handleWhenStockFound(Stock stockFound, StockRequest stockRequest) {
		// Already stock found for given date

		// Update the found stockFound
		stockFound.setOpeningStock(stockFound.getOpeningStock() + stockRequest.getOpeningStock());
		stockFound.setClosingStock(stockFound.getClosingStock() + stockRequest.getClosingStock());

		stockFound.setTotalStock(
				stockFound.getTotalStock() + stockRequest.getOpeningStock() - stockRequest.getClosingStock());
		return stocksRepository.save(stockFound);
	}

	/**
	 * Handle when stock not found.
	 *
	 * @param stockRequest the stock request
	 * @param product the product
	 * @return the stock
	 */
	private Stock handleWhenStockNotFound(StockRequest stockRequest, Product product) {
		// No stock found for given date
		// Query to check stock exist for previous dates
		Optional<Stock> previousStockAvailable = findPreviousStocks(stockRequest);
		return stocksRepository.save(
				createStockWithTotalStock(stockRequest, product, previousStockAvailable.map(Stock::getTotalStock)));
	}

	/**
	 * Find previous stocks.
	 *
	 * @param stockRequest the stock request
	 * @return the optional
	 */
	private Optional<Stock> findPreviousStocks(StockRequest stockRequest) {
		Query queryToCheckStockForPreviousDates = new Query();
		Criteria criteria = matchingProductCriteria(stockRequest).and(STOCK_DATE).lt(stockRequest.getStockDate());
		queryToCheckStockForPreviousDates.addCriteria(criteria).with(Sort.by(Sort.Direction.DESC, STOCK_DATE)).limit(1);
		return Optional.ofNullable(mongoTemplate.findOne(queryToCheckStockForPreviousDates, Stock.class));
	}

	/**
	 * Creates the stock with total stock.
	 *
	 * @param stockRequest the stock request
	 * @param product the product
	 * @param totalStock the total stock
	 * @return the stock
	 */
	private Stock createStockWithTotalStock(StockRequest stockRequest, Product product, Optional<Integer> totalStock) {
		Stock stock = new Stock();
		stock.setProduct(product);
		stock.setStockDate(stockRequest.getStockDate());
		stock.setOpeningStock(stockRequest.getOpeningStock());
		stock.setClosingStock(stockRequest.getClosingStock());
		if (totalStock.isPresent()) {
			// Stock found for previous date, get the totalStock count foreword, and create
			// new stock
			stock.setTotalStock(totalStock.get() + stockRequest.getOpeningStock() - stockRequest.getClosingStock());
		} else {
			// No stock found for previous dates as well, create new stock
			stock.setTotalStock(stockRequest.getOpeningStock() - stockRequest.getClosingStock());
		}
		return stock;
	}

	
	/**
	 * Find by shop and stock date less than equal page.
	 *
	 * @param shopCode    the shop code
	 * @param stockDate   the stock date
	 * @param pageRequest the page request
	 * @return the page
	 */
	public Page<Stock> findByShopAndStockDateLessThanEqual(String shopCode, Date stockDate, Pageable pageRequest) {
		return stocksRepository.findByStockDateLessThanEqualAndProduct_ShopCode(stockDate, shopCode, pageRequest);
	}

	/**
	 * Find by shop code page.
	 *
	 * @param shopCode    the shop code
	 * @param pageRequest the page request
	 * @return the page
	 */
	public Page<Stock> findByShopCode(String shopCode, Pageable pageRequest) {
		return stocksRepository.findByProduct_ShopCode(shopCode, pageRequest);
	}

	/**
	 * Find by stock date less than equal page.
	 *
	 * @param stockDate   the stock date
	 * @param pageRequest the page request
	 * @return the page
	 */
	public Page<Stock> findByStockDateLessThanEqual(Date stockDate, Pageable pageRequest) {
		return stocksRepository.findByStockDateLessThanEqual(stockDate, pageRequest);
	}

	public Page<Stock> search(String shopCode, Date stockDate, Optional<String> productName, Optional<String> packaging,
			PageRequest pageRequest) {
		
		if (productName.isPresent() && packaging.isPresent()) {
			return stocksRepository.findByStockDateLessThanEqualAndProduct_ShopCodeAndProduct_NameAndProduct_Packaging(
					stockDate, shopCode, productName.get(), packaging.get(), pageRequest);
		} else if (productName.isPresent() && !packaging.isPresent()) {
			return stocksRepository.findByStockDateLessThanEqualAndProduct_ShopCodeAndProduct_Name(stockDate, shopCode,
					productName.get(), pageRequest);
		} else if (!productName.isPresent() && packaging.isPresent()) {
			return stocksRepository.findByStockDateLessThanEqualAndProduct_ShopCodeAndProduct_Packaging(stockDate,
					shopCode, packaging.get(), pageRequest);
		}
		
		return stocksRepository.findByStockDateLessThanEqualAndProduct_ShopCode(stockDate, shopCode, pageRequest);
	}
}
