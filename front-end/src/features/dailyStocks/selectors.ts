import { ProductState,StockState } from './types';

export const getStocks = (state: StockState) => state.stockState.stocks;
export const getProducts = (state: ProductState) => state.productState.products;
export const getSelectedProduct = (state: ProductState) =>
    state.productState.selectedProduct;
export const getShowEditProductDialog = (state: ProductState) =>
    state.productState.showEditDialog;
