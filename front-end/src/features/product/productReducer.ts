import {
    CREATE_PRODUCT,
    DELETE_PRODUCT,
    FETCH_PRODUCTS,
    Product,
    ProductActionTypes,
    ProductState,
    SELECT_PRODUCT,
    TOGGLE_EDIT_DIALOG,
} from './types';

const initialState: ProductState = {
    productState: {
        products: [],
        showEditDialog: false,
        selectedProduct: null,
    },
};

export default (
    state = initialState.productState,
    action: ProductActionTypes
) => {
    switch (action.type) {
        case FETCH_PRODUCTS:
            return {
                ...state,
                products: action.products,
            };
        case CREATE_PRODUCT:
            return {
                ...state,
                products: [...state.products, action.product],
            };
        case DELETE_PRODUCT:
            return {
                ...state,
                products: [
                    ...state.products.filter(
                        (p: Product) => p.id !== action.product.id
                    ),
                ],
            };
        case SELECT_PRODUCT:
            return {
                ...state,
                selectedProduct: action.product,
            };
        case TOGGLE_EDIT_DIALOG:
            return {
                ...state,
                showEditDialog: !state.showEditDialog,
            };
        default:
            return state;
    }
};
