export const FETCH_PRODUCTS = 'FETCH_PRODUCTS';
export const CREATE_PRODUCT = 'CREATE_PRODUCT';
export const UPDATE_PRODUCT = 'UPDATE_PRODUCT';
export const DELETE_PRODUCT = 'DELETE_PRODUCT';
export const SELECT_PRODUCT = 'SELECT_PRODUCT';
export const TOGGLE_EDIT_DIALOG = 'TOGGLE_EDIT_DIALOG';

export interface FetchProductsAction {
    type: typeof FETCH_PRODUCTS;
    products: Array<Product>;
    shopcode: String;
}

export interface CreateProductAction {
    type: typeof CREATE_PRODUCT;
    product: Product;
}

export interface UpdateProductAction {
    type: typeof UPDATE_PRODUCT;
    product: Product;
}

export interface DeleteProductAction {
    type: typeof DELETE_PRODUCT;
    product: Product;
}

export interface SelectProductAction {
    type: typeof SELECT_PRODUCT;
    product: Product | null;
}

export interface ToggleEditProductDialogAction {
    type: typeof TOGGLE_EDIT_DIALOG;
}

export type ProductActionTypes =
    | FetchProductsAction
    | CreateProductAction
    | UpdateProductAction
    | DeleteProductAction
    | SelectProductAction
    | ToggleEditProductDialogAction;

export interface Product {
    id?: string;
    code: string;
    name: string;
    packaging: string;
    shopCode: string;
    createDate?: number;
    updateDate?: number;
}

export interface ProductState {
    productState: {
        products: Product[];
        showEditDialog: boolean;
        selectedProduct: Product | null;
    };
}
