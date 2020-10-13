import {
    FETCH_PRODUCTS,
    FetchProductsAction,
    Product,
    Stocks,
    FETCH_STOCKS,
    FetchStocksAction,
    setProductNameAction,
    SET_PRODUCT_NAME,
    setProductPackagingAction,
    SET_PRODUCT_PACKAGING,
    setStockDateAction,
    SET_STOCK_DATE,
    setOpenStockAction,
    SET_OPEN_STOCK,
    setCLoseStockAction,
    SET_CLOSE_STOCK,
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

export const setProductName = (productName: string): setProductNameAction => {
    return {
        type: SET_PRODUCT_NAME,
        productName,
    };
};

export const setProductPackaging = (
    productPackaging: string
): setProductPackagingAction => {
    return {
        type: SET_PRODUCT_PACKAGING,
        productPackaging,
    };
};

export const setStockDate = (stockDate: string): setStockDateAction => {
    return {
        type: SET_STOCK_DATE,
        stockDate,
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
