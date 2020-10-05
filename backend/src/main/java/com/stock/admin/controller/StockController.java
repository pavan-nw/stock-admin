package com.stock.admin.controller;

import com.stock.admin.model.entity.Stock;
import com.stock.admin.model.request.StockRequest;
import com.stock.admin.model.response.Response;
import com.stock.admin.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Stock controller.
 */
@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    @ResponseBody
    public Response createOrUpdateStock(@RequestBody StockRequest stockRequest) {
        Stock stock = stockService.createOrUpdate(stockRequest);
        return Response.buildResponse(Stock.type, stock, true);
    }

    @GetMapping
    @ResponseBody
    public Response getAllStocks() {
        return Response.buildResponse(Stock.type, stockService.getAll(), true);
    }
}
