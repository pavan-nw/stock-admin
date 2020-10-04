export const FETCH_PRODUCTS = 'FETCH_PRODUCTS';
export const CREATE_PRODUCT = 'CREATE_PRODUCT';
export const UPDATE_PRODUCT = 'UPDATE_PRODUCT';
export const DELETE_PRODUCT = 'DELETE_PRODUCT';
export const SELECT_PRODUCT = 'SELECT_PRODUCT';
export const TOGGLE_EDIT_DIALOG = 'TOGGLE_EDIT_DIALOG';

export const FETCH_STOCKS = 'FETCH_STOCKS';
export const CREATE_STOCK = 'CREATE_STOCK';
export const UPDATE_STOCK = 'UPDATE_STOCK';
export const SEARCH_STOCK = 'SEARCH_STOCK';

export interface FetchProductsAction {
    type: typeof FETCH_PRODUCTS;
    products: Array<Product>;
    shopcode: String;
}

export interface FetchStocksAction {
    type: typeof FETCH_STOCKS;
    stocks: Array<Stocks>;
    shopCode: string;
}

export interface CreateStocksAction {
    type: typeof CREATE_STOCK;
    stock: Stocks;    
}

export interface UpdateStockAction {
    type: typeof UPDATE_STOCK;
    stock: Stocks;
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

export type StockActionTypes =
    | FetchStocksAction
    | CreateStocksAction
    | UpdateStockAction;   

export interface Product {
    id?: string;
    code: string;
    name: string;
    packaging: string;
    shopCode?: string;
    createdat?: number;
    updatedat?: number;
}

export interface Stocks {
    id?: string;
    product: Product;
    openingStock: string;
    closingStock: string;
    stockDate: string;
    totalStock:number;
    createdAt?: number;
    updatedAt?: number;
}

export interface ProductState {
    productState: {
        products: Product[];
        showEditDialog: boolean;
        selectedProduct: Product | null;
    };
}

export interface StockState {
    stockState: {
        stocks: Stocks[];
        showEditDialog: boolean;        
    };
}
