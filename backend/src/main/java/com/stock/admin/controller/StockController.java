package com.stock.admin.controller;

import static com.stock.admin.utils.Helper.pageRequestFor;
import static com.stock.admin.utils.StockAdminConstants.STOCK_DATE;

import com.stock.admin.model.entity.Stock;
import com.stock.admin.model.request.StockRequest;
import com.stock.admin.model.request.StockSearchRequest;
import com.stock.admin.model.response.PagedResponse;
import com.stock.admin.model.response.Response;
import com.stock.admin.service.ExportService;
import com.stock.admin.service.StockService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Stock controller.
 */
@RestController
@RequestMapping("/api/stocks")
@CrossOrigin
public class StockController {

	private final StockService stockService;

	private final ExportService exportService;

	/**
	 * Instantiates a new Stock controller.
	 *
	 * @param stockService the stock service
	 */
	@Autowired
	public StockController(StockService stockService, ExportService exportService) {
		this.stockService = stockService;
		this.exportService = exportService;
	}

	/**
	 * Create or update stock response.
	 *
	 * @param stockRequest the stock request
	 * @return the response
	 */
	@PostMapping
	@ResponseBody
	public Response createOrUpdateStock(@RequestBody StockRequest stockRequest) {
		Stock stock = stockService.createOrUpdate(stockRequest);
		return Response.buildResponse(Stock.type, stock, true);
	}

	/**
	 * Download the stocks.
	 *
	 * @param fromDate the from date
	 * @param toDate   the to date
	 * @param shopCode the shop code
	 * @return the response entity
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@GetMapping(path = "/download")
	public ResponseEntity<Resource> download(
			@RequestParam(name = "fromDate") @DateTimeFormat(pattern = "dd-MM-yyyy", iso = DateTimeFormat.ISO.DATE) Date fromDate,
			@RequestParam(name = "toDate") @DateTimeFormat(pattern = "dd-MM-yyyy", iso = DateTimeFormat.ISO.DATE) Date toDate,
			@RequestParam(name = "shopCode") String shopCode) throws IOException {

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=stocks.pdf");
		File file = exportService.getFileToExport(fromDate, toDate, shopCode);
		InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
		return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.APPLICATION_PDF)
				.body(inputStreamResource);
	}
    /**
     * Gets all stocks.
     *
     * @param shopCode  the shop code
     * @param stockDate the stock date
     * @param pageNum   the page num
     * @param size      the size
     * @param sortType  the sort type
     * @return the all stocks
     */
    @GetMapping
    @ResponseBody
    public PagedResponse getAllStocks(@RequestParam(name = "shopCode", required = true) String shopCode,
                                      @RequestParam(name = "stockDate")
                                      @DateTimeFormat(pattern = "dd-MM-yyyy",
                                              iso = DateTimeFormat.ISO.DATE) Optional<Date> stockDate,
                                      @RequestParam(name = "page", defaultValue = "1") int pageNum,
                                      @RequestParam(name = "size", defaultValue = "500") int size,
                                      @RequestParam(name = "sort", defaultValue = "DESC") String sortType) {

        PageRequest pageRequest = pageRequestFor(pageNum, size, sortType, STOCK_DATE);
        Page<Stock> page = stockDate.map(date -> stockService
                .findByShopAndStockDateLessThanEqual(shopCode, date, pageRequest))
                .orElseGet(() -> stockService.findByShopCode(shopCode, pageRequest));

        return PagedResponse.buildPagedResponse(Stock.type,
                page.getContent(),
                true,
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.isLast(),
                page.getTotalElements());
    }
    
	@PostMapping(path = "/search")
	@ResponseBody
	public PagedResponse getStocks(@RequestBody StockSearchRequest stockSearchRequest) {
		
		PageRequest pageRequest = pageRequestFor(stockSearchRequest.getPageNum(), stockSearchRequest.getSize(),
				stockSearchRequest.getSortType(), STOCK_DATE);
		Page<Stock> page = stockService.search(stockSearchRequest.getShopCode(), stockSearchRequest.getStockDate(),
				Optional.ofNullable(stockSearchRequest.getProductName()), Optional.ofNullable(stockSearchRequest.getPackaging()), pageRequest);

		return PagedResponse.buildPagedResponse(Stock.type, page.getContent(), true, page.getNumber(), page.getSize(),
				page.getTotalPages(), page.isLast(), page.getTotalElements());
	}
}
