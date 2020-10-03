import {
    CREATE_PRODUCT,
    CreateProductAction,
    DELETE_PRODUCT,
    DeleteProductAction,
    FETCH_PRODUCTS,
    FetchProductsAction,
    Product,
    SELECT_PRODUCT,
    SelectProductAction,
    TOGGLE_EDIT_DIALOG,
    ToggleEditProductDialogAction,
    UPDATE_PRODUCT,
    UpdateProductAction,
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

export const createProduct = (product: Product): CreateProductAction => {
    return {
        type: CREATE_PRODUCT,
        product,
    };
};

export const updateProduct = (product: Product): UpdateProductAction => {
    return {
        type: UPDATE_PRODUCT,
        product,
    };
};

export const deleteProduct = (product: Product): DeleteProductAction => {
    return {
        type: DELETE_PRODUCT,
        product,
    };
};

export const selectProduct = (product: Product | null): SelectProductAction => {
    return {
        type: SELECT_PRODUCT,
        product: product,
    };
};

export const toggleShowEditDialog = (): ToggleEditProductDialogAction => {
    return {
        type: TOGGLE_EDIT_DIALOG,
    };
};
