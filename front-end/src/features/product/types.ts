export const FETCH_PRODUCTS = 'FETCH_PRODUCTS';

export interface FetchProductsAction {
    type: typeof FETCH_PRODUCTS;
    products: Array<Product>;
    shopcode: String;
}

export type ProductActionTypes = FetchProductsAction;

export interface Product {
    id: string;
    code: string;
    name: string;
    packaging: string;
    shopCode: string;
    createdat: number;
    updatedat: number;
}

export interface ProductState {
    productState: {
        products: Product[];
    };
}
