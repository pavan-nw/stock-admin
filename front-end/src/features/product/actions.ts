import { FETCH_PRODUCTS, FetchProductsAction, Product } from './types';

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
