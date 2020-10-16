package com.stock.admin.model.request;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * The type Stock request.
 */
public class StockRequest extends Request {

    private String productName;
    private String packaging;
    @DateTimeFormat(pattern = "dd-MM-yyyy", iso = DateTimeFormat.ISO.DATE)
    private Date stockDate;
    private int openingStock = 0;
    private int closingStock = 0;

    /**
     * Gets product name.
     *
     * @return the product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets product name.
     *
     * @param productName the product name
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Gets packaging.
     *
     * @return the packaging
     */
    public String getPackaging() {
        return packaging;
    }

    /**
     * Sets packaging.
     *
     * @param packaging the packaging
     */
    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    /**
     * Gets stock date.
     *
     * @return the stock date
     */
    public Date getStockDate() {
        return stockDate;
    }

    /**
     * Sets stock date.
     *
     * @param stockDate the stock date
     */
    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }

    /**
     * Gets opening stock.
     *
     * @return the opening stock
     */
    public int getOpeningStock() {
        return openingStock;
    }

    /**
     * Sets opening stock.
     *
     * @param openingStock the opening stock
     */
    public void setOpeningStock(int openingStock) {
        this.openingStock = openingStock;
    }

    /**
     * Gets closing stock.
     *
     * @return the closing stock
     */
    public int getClosingStock() {
        return closingStock;
    }

    /**
     * Sets closing stock.
     *
     * @param closingStock the closing stock
     */
    public void setClosingStock(int closingStock) {
        this.closingStock = closingStock;
    }
}
