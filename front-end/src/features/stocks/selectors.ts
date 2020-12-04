import { ProductState, StockState } from './types';

export const getStocks = (state: StockState) => state.stockState.stocks;
export const getProducts = (state: ProductState) => state.productState.products;
export const getSelectedProduct = (state: ProductState) =>
    state.productState.selectedProduct;
export const getCurrentStockProduct = (state: StockState) =>
    state.stockState.currentStock.product;
export const getCurrentStockProductPackaging = (state: StockState) =>
    state.stockState.currentStock.packaging;
export const getCurrentStockDate = (state: StockState) =>
    state.stockState.currentStock.stockDate;
export const getOpeningStock = (state: StockState) =>
    state.stockState.currentStock.openingStocks;
export const getClosingStock = (state: StockState) =>
    state.stockState.currentStock.closingStocks;