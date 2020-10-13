export const FETCH_PRODUCTS = 'FETCH_PRODUCTS';
export const CREATE_PRODUCT = 'CREATE_PRODUCT';
export const UPDATE_PRODUCT = 'UPDATE_PRODUCT';
export const DELETE_PRODUCT = 'DELETE_PRODUCT';
export const SELECT_PRODUCT = 'SELECT_PRODUCT';
export const TOGGLE_EDIT_DIALOG = 'TOGGLE_EDIT_DIALOG';

export const SET_PRODUCT_NAME = 'SET_PRODUCT_NAME';
export const SET_PRODUCT_PACKAGING = 'SET_PRODUCT_PACKAGING';
export const SET_STOCK_DATE = 'SET_STOCK_DATE';
export const SET_CLOSE_STOCK = 'SET_CLOSE_STOCK';
export const SET_OPEN_STOCK = 'SET_OPEN_STOCK';


export const FETCH_STOCKS = 'FETCH_STOCKS';
export const CREATE_STOCK = 'CREATE_STOCK';
export const UPDATE_STOCK = 'UPDATE_STOCK';
export const SEARCH_STOCK = 'SEARCH_STOCK';

export interface FetchProductsAction {
    type: typeof FETCH_PRODUCTS;
    products: Array<Product>;
    shopcode: String;
}

export interface setProductNameAction{
    type :typeof SET_PRODUCT_NAME;
    productName: string;
}

export interface setProductPackagingAction{
    type :typeof SET_PRODUCT_PACKAGING;
    productPackaging: string;
}

export interface setStockDateAction{
    type :typeof SET_STOCK_DATE;
    stockDate: string;
}

export interface setOpenStockAction{
    type :typeof SET_OPEN_STOCK;
    openStock: string;
}

export interface setCLoseStockAction{
    type :typeof SET_CLOSE_STOCK;
    closeStock: string;
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
export interface DeleteProductAction {
    type: typeof DELETE_PRODUCT;
    product: Product;
}

export type StockActionTypes =
    | FetchStocksAction
    | CreateStocksAction
    | UpdateStockAction
    | setProductNameAction
    | setProductPackagingAction
    | setStockDateAction
    | setOpenStockAction
    | setCLoseStockAction;

export interface Product {
    id?: string;
    code: string;
    name: string;
    packaging: string;
    shopCode?: string;
    createdAt?: number;
    updateAt?: number;
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

export interface StockDetails{
    productName:string;
    packaging:string | null;
    stockDate:string;
    openingStocks:number;
    closingStocks:number;
}


export interface StockState {
    stockState: {
        stocks: Stocks[];
        showEditDialog: boolean; 
        currentStock: StockDetails;
    };
}
