import {
    StockActionTypes,
    UPDATE_STOCK,
    StockState,
    FETCH_STOCKS,
    StockDetails,
    SET_PRODUCT,
    SET_PRODUCT_PACKAGING,
    SET_STOCK_DATE,
    SET_OPEN_STOCK,
    SET_CLOSE_STOCK,
    CLEAR_CURRENT_STOCK,
    Product,
    LocalPackaging
} from './types';

const product: Product = {
    id: '',
    code: '',
    name: '',
    packaging: '',
    shopCode: '',
    createdAt: 0,
    updateAt: 0,
};

const packaging: LocalPackaging = {
    name: '',
};
const currentStock: StockDetails = {
    product: product,
    packaging: packaging,
    stockDate: new Date(),
    closingStocks: 0,
    openingStocks: 0,
};

const initialState: StockState = {
    stockState: {
        stocks: [],
        currentStock: currentStock        
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
        case SET_PRODUCT:
            return {
                ...state,
                currentStock: {
                    ...state.currentStock,
                    product: action.product,
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
                currentStock: {
                    ...state.currentStock,
                    stockDate: action.stockDate,
                },
            };
        case SET_OPEN_STOCK:
            return {
                ...state,
                currentStock: {
                    ...state.currentStock,
                    openingStocks: action.openStock,
                },
            };
        case SET_CLOSE_STOCK:
            return {
                ...state,
                currentStock: {
                    ...state.currentStock,
                    closingStocks: action.closeStock,
                },
            };
        case CLEAR_CURRENT_STOCK:
            return {
                ...state,
                currentStock: currentStock,
            };        
        default:
            return state;
    }
};
