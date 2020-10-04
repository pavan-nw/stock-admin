import { ThunkAction } from 'redux-thunk';
import {
    createProduct,
    deleteProduct,
    fetchProducts,
    fetchStocks,
    toggleShowEditDialog,
    updateProduct,
} from './actions';
import { Product, ProductActionTypes, ProductState, Stocks, StockState,StockActionTypes } from './types';
import {
    hideSpinnerDialog,
    showSpinnerDialog,
    showToast,
} from '../common/actions';
import { CommonActionTypes } from '../common/types';
import { checkSuccess, getErrorMessageToShow } from '../../helpers/utils';
import axiosInstance from '../../config/axiosConfig';
import {
    creatingProduct,
    deletingProduct,
    errorOccurred,
    fetchingProduct,
    fetchingStocks,
    updatingProduct,
} from '../../helpers/constants';

export const getStocks = (): ThunkAction<
    void,
    StockState,
    unknown,
    StockActionTypes | CommonActionTypes
> => async (dispatch, getState) => {
    try {
        dispatch(showSpinnerDialog(fetchingStocks));
        const response = await axiosInstance.get('/daily-stocks');
        const responseJson = await response.data;
        dispatch(hideSpinnerDialog());
        if (checkSuccess(responseJson)) {
            console.log(`fetching stocks success ${responseJson.payload}`);
            dispatch(fetchStocks('shopCode', responseJson.payload));
        } else {
            dispatch(showToast(errorOccurred, responseJson.status, 'error'));
        }
    } catch (e) {
        console.log(`fetching stocks failed`);
        dispatch(hideSpinnerDialog());
        dispatch(showToast(errorOccurred, getErrorMessageToShow(e), 'error'));
    }
};

export const addProduct = (
    product: Product
): ThunkAction<
    void,
    ProductState,
    unknown,
    ProductActionTypes | CommonActionTypes
> => async (dispatch, getState) => {
    try {
        dispatch(showSpinnerDialog(creatingProduct));
        const createProductRequest = {
            type: 'product',
            product,
        };
        const response = await axiosInstance.post(
            '/products',
            createProductRequest
        );
        const responseJson = await response.data;
        dispatch(hideSpinnerDialog());
        if (checkSuccess(responseJson)) {
            dispatch(createProduct(responseJson.payload));
        } else {
            dispatch(showToast(errorOccurred, responseJson.status, 'error'));
        }
    } catch (e) {
        dispatch(hideSpinnerDialog());
        dispatch(showToast(errorOccurred, getErrorMessageToShow(e), 'error'));
    }
};

export const editProduct = (
    product: Product
): ThunkAction<
    void,
    ProductState,
    unknown,
    ProductActionTypes | CommonActionTypes
> => async (dispatch, getState) => {
    try {
        dispatch(showSpinnerDialog(updatingProduct));
        const editProductRequest = {
            type: 'product',
            product,
        };
        const response = await axiosInstance.put(
            `/products/${product.id}`,
            editProductRequest
        );

        const responseJson = await response.data;
        dispatch(hideSpinnerDialog());
        if (checkSuccess(responseJson)) {
            dispatch(updateProduct(responseJson.payload));
            dispatch(toggleShowEditDialog());
        } else {
            dispatch(showToast(errorOccurred, responseJson.status, 'error'));
        }
    } catch (e) {
        dispatch(hideSpinnerDialog());
        dispatch(showToast(errorOccurred, getErrorMessageToShow(e), 'error'));
    }
};

export const removeProduct = (
    productId: string
): ThunkAction<
    void,
    ProductState,
    unknown,
    ProductActionTypes | CommonActionTypes
> => async (dispatch, getState) => {
    try {
        dispatch(showSpinnerDialog(deletingProduct));
        const response = await axiosInstance.delete(`/products/${productId}`);
        const responseJson = await response.data;
        dispatch(hideSpinnerDialog());
        if (checkSuccess(responseJson)) {
            dispatch(deleteProduct(responseJson.payload));
        } else {
            dispatch(showToast(errorOccurred, responseJson.status, 'error'));
        }
    } catch (e) {
        dispatch(hideSpinnerDialog());
        dispatch(showToast(errorOccurred, getErrorMessageToShow(e), 'error'));
    }
};
