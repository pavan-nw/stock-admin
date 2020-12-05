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

	/** The constant something went wrong. */
	String SOMETHING_WENT_WRONG = "Something Went Wrong. Please contact the Admin";

	/** The constant invalid shop code. */
	String INVALID_SHOP_CODE = "Invalid Shop code";

	/** The constant no stocks found. */
	String NO_STOCKS_FOUND = "No Stocks Found for the selected Date range";

	/** The invalid token. */
	String INVALID_TOKEN = "Invalid Token";

	/** The token missing. */
	String TOKEN_MISSING = "Token missing";

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

	/** The api users. */
	String API_GET_SHOPCODES = "/api/shops";

	/** The token expiry hours. */
	int TOKEN_EXPIRY_HOURS = 5;

	/** The total stocks. */
	String TOTAL_STOCKS = "Total Stocks";
	
	/** The outgoing stock count. */
	String OUTGOING_STOCK_COUNT = "Outgoing Stock Count";
	
	/** The incoming stock count. */
	String INCOMING_STOCK_COUNT = "Incoming Stock Count";
	
	/** The stock date label. */
	String STOCK_DATE_LABEL = "Stock Date";
	
	/** The product packaging. */
	String PRODUCT_PACKAGING = "Product Packaging";
	
	/** The product name. */
	String PRODUCT_NAME = "Product Name";
	
	/** The sl no. */
	String SL_NO = "Sl.no";
	
	/** The stock report. */
	String STOCK_REPORT = "Stock Report";
	
	/** The stock admin. */
	String STOCK_ADMIN = "Stock Admin";
	
	/** The to date. */
	String TO_DATE = "To Date :- ";
	
	/** The from date. */
	String FROM_DATE = "From Date :- ";
	
	/** The exported date. */
	String EXPORTED_DATE = "Exported Date :- ";
}
