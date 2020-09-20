import { FETCH_PRODUCTS, ProductActionTypes } from './types';

const initialState = {
    products: [],
};

export default (state = initialState, action: ProductActionTypes) => {
    switch (action.type) {
        case FETCH_PRODUCTS:
            return {
                ...state,
                products: action.products,
            };
        default:
            return state;
    }
};
