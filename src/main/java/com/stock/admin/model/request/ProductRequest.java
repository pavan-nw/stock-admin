package com.stock.admin.model.request;

import com.stock.admin.model.entity.Product;

/**
 * The type Create product request.
 */
public class ProductRequest extends Request {

    private Product product;

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
}
