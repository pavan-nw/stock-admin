import {
    FETCH_PRODUCTS,
    FetchProductsAction,
    Product,
    Stocks,
    FETCH_STOCKS,
    FetchStocksAction,
    setProductAction,
    SET_PRODUCT,
    setProductPackagingAction,
    SET_PRODUCT_PACKAGING,
    setStockDateAction,
    SET_STOCK_DATE,
    setOpenStockAction,
    SET_OPEN_STOCK,
    setCLoseStockAction,
    SET_CLOSE_STOCK,
    ClearCurrentStockAction,
    CLEAR_CURRENT_STOCK,
    LocalPackaging 
} from './types';

export const fetchProducts = (
    shopcode: string,
    products: Product[]
): FetchProductsAction => {
    return {
        type: FETCH_PRODUCTS,
        shopcode,
        products,
    };
};

export const fetchStocks = (
    shopCode: string,
    stocks: Stocks[]
): FetchStocksAction => {
    return {
        type: FETCH_STOCKS,
        shopCode,
        stocks,
    };
};

export const setProduct = (product: Product): setProductAction => {
    return {
        type: SET_PRODUCT,
        product,
    };
};

export const setProductPackaging = (
    productPackaging: LocalPackaging
): setProductPackagingAction => {
    return {
        type: SET_PRODUCT_PACKAGING,
        productPackaging,
    };
};

export const setStockDate = (stockDate: Date): setStockDateAction => {
    return {
        type: SET_STOCK_DATE,
        stockDate,
    };
};

export const clearCurrentStock = (): ClearCurrentStockAction => {
    return {
        type: CLEAR_CURRENT_STOCK,
    };
};

export const setOpenStock = (openStock: string): setOpenStockAction => {
    return {
        type: SET_OPEN_STOCK,
        openStock,
    };
};

export const setCloseStock = (closeStock: string): setCLoseStockAction => {
    return {
        type: SET_CLOSE_STOCK,
        closeStock,
    };
};