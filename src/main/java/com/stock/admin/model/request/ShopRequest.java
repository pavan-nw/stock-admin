package com.stock.admin.model.request;

import com.stock.admin.model.entity.Shop;

/**
 * The type Shop request.
 */
public class ShopRequest extends Request {
    private Shop shop;

    /**
     * Gets shop.
     *
     * @return the shop
     */
    public Shop getShop() {
        return shop;
    }

    /**
     * Sets shop.
     *
     * @param shop the shop
     */
    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
