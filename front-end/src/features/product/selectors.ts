import { ProductState } from './types';

export const getProducts = (state: ProductState) => state.productState.products;
