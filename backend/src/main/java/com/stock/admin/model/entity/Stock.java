package com.stock.admin.model.entity;

import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The type Stocks.
 */
@Document("stocks")
public class Stock {
    /**
     * The constant type.
     */
    public static final String type = "stock";

    @Id
    private String id;
    private Product product;
    private int openingStock;
    private int closingStock;
    private Date stockDate;
    private int totalStock;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets product.
     *
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets product.
     *
     * @param product the product
     */
    public void setProduct(Product product) {
        this.product = product;
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
     * Gets total stock.
     *
     * @return the total stock
     */
    public int getTotalStock() {
        return totalStock;
    }

    /**
     * Sets total stock.
     *
     * @param totalStock the total stock
     */
    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }

    /**
     * Gets created at.
     *
     * @return the created at
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets created at.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets updated at.
     *
     * @return the updated at
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets updated at.
     *
     * @param updatedAt the updated at
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
