import {    
    StockActionTypes,    
    UPDATE_STOCK,
    StockState,
    FETCH_STOCKS,
    StockDetails,
    SET_PRODUCT_NAME,
    SET_PRODUCT_PACKAGING,
    SET_STOCK_DATE,
    SET_OPEN_STOCK,
    SET_CLOSE_STOCK,
} from './types';

let currentStock: StockDetails = {
    productName: '',
    packaging: null,
    stockDate: new Date().toDateString(),
    closingStocks: 0,
    openingStocks: 0,
};
const initialState: StockState = {
    stockState: {
        stocks: [],
        showEditDialog: false,
        currentStock: currentStock,
    },
};

export default (state = initialState.stockState, action: StockActionTypes) => {    
    switch (action.type) {
        case FETCH_STOCKS:
            return {
                ...state,
                stocks: action.stocks,
            };
        case UPDATE_STOCK:
            return {
                ...state,
                products: [...state.stocks, action.stock],
            };
        case SET_PRODUCT_NAME:           
            return {
                ...state,
                currentStock: {
                    ...state.currentStock,
                    productName: action.productName,
                },
            };
        case SET_PRODUCT_PACKAGING:            
            return {
                ...state,
                currentStock: {
                    ...state.currentStock,
                    packaging: action.productPackaging,
                },
            };
        case SET_STOCK_DATE:
            return {
                ...state,
                currentStock: { ...state.currentStock, stockDate: action.stockDate },
            };
        case SET_OPEN_STOCK:
            return {
                ...state,
                currentStock: { ...state.currentStock, openingStocks: action.openStock },
            };
        case SET_CLOSE_STOCK:
            return {
                ...state,
                currentStock: { ...state.currentStock, closingStocks: action.closeStock },
            };      
        default:
            return state;
    }
};
