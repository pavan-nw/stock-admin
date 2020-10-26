package com.stock.admin.model.request;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * The type Stock request.
 */
public class StockSearchRequest extends Request {

    private String productName;
    private String packaging;
    @DateTimeFormat(pattern = "dd-MM-yyyy", iso = DateTimeFormat.ISO.DATE)
    private Date stockDate;    
    private String shopCode;
    int pageNum=1;
    int size=500;
    String sortType="DESC";
    

    /**
     * Gets the shop code.
     *
     * @return the shop code
     */
    public String getShopCode() {
		return shopCode;
	}

	/**
	 * Sets the shop code.
	 *
	 * @param shopCode the new shop code
	 */
	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

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

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}    
}
