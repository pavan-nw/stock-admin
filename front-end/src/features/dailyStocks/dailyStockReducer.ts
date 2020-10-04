import {
    CREATE_PRODUCT,
    DELETE_PRODUCT,
    FETCH_PRODUCTS,
    Product,
    ProductActionTypes,
    ProductState,
    SELECT_PRODUCT,
    TOGGLE_EDIT_DIALOG,
    Stocks,
    StockActionTypes,
    CREATE_STOCK,
    UPDATE_STOCK,
    StockState,
    FETCH_STOCKS
} from './types';

const initialState: StockState = {
    stockState: {
        stocks: [],
        showEditDialog: false        
    },
};

export default (
    state = initialState.stockState,
    action: StockActionTypes
) => {
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
        // case DELETE_PRODUCT:
        //     return {
        //         ...state,
        //         products: [
        //             ...state.products.filter(
        //                 (p: Product) => p.id !== action.product.id
        //             ),
        //         ],
        //     };
        // case SELECT_PRODUCT:
        //     return {
        //         ...state,
        //         selectedProduct: action.product,
        //     };
        // case TOGGLE_EDIT_DIALOG:
        //     return {
        //         ...state,
        //         showEditDialog: !state.showEditDialog,
        //     };
        default:
            return state;
    }
};
