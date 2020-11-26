package com.stock.admin.utils;

/**
 * The interface Stock admin constants.
 */
public interface StockAdminConstants {

    /**
     * The constant STOCK_DATE.
     */
    String STOCK_DATE = "stockDate";
    /**
     * The constant PATH_PRODUCT_NAME.
     */
    String PATH_PRODUCT_NAME = "product.name";
    /**
     * The constant PATH_PRODUCT_PACKAGING.
     */
    String PATH_PRODUCT_PACKAGING = "product.packaging";
    
    String PATH_PRODUCT_SHOP_CODE = "product.shopCode";
    /**
     * The constant TOTAL_STOCK.
     */
    String TOTAL_STOCK = "totalStock";
    /**
     * The constant PRODUCT_DOES_NOT_EXISTS.
     */
    String PRODUCT_DOES_NOT_EXISTS = "Product does not exists";
    
    /** The invalid token. */
    String INVALID_TOKEN = "Invalid Token";
    
	/** The token missing. */
	String TOKEN_MISSING = "Token missing";
	
	/** The invalid shopcode. */
	String INVALID_SHOPCODE = "INVALID SHOPCODE";

	/** The user disabled. */
	String USER_DISABLED = "USER_DISABLED";
	
	/** The bearer. */
	String BEARER = "Bearer ";

	/** The authorization. */
	String AUTHORIZATION = "Authorization";
	
	/** The invalid credentials. */
	String INVALID_CREDENTIALS = "INVALID_CREDENTIALS";
	
	/** The api users. */
	String API_USERS = "/api/users/**";
	
	/** The token expiry hours. */
	int TOKEN_EXPIRY_HOURS = 5;
}
