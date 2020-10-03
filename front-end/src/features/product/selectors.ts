import { ProductState } from './types';

export const getProducts = (state: ProductState) => state.productState.products;
export const getSelectedProduct = (state: ProductState) =>
    state.productState.selectedProduct;
export const getShowEditProductDialog = (state: ProductState) =>
    state.productState.showEditDialog;
