import { ThunkAction } from 'redux-thunk';
import {
    createProduct,
    deleteProduct,
    fetchProducts,
    toggleShowEditDialog,
    updateProduct,
} from './actions';
import { Product, ProductActionTypes, ProductState } from './types';
import {
    hideSpinnerDialog,
    showSpinnerDialog,
    showToast,
} from '../common/actions';
import { CommonActionTypes } from '../common/types';
import {
    checkSuccess,
    getErrorMessageToShow,
} from '../../helpers/utils';
import axiosInstance from '../../config/axiosConfig';
import {
    creatingProduct,
    deletingProduct,
    errorOccurred,
    fetchingProduct,
    updatingProduct,
} from '../../helpers/constants';

export const getProducts = (
    shopCode: string
): ThunkAction<
    void,
    ProductState,
    unknown,
    ProductActionTypes | CommonActionTypes
> => async (dispatch, getState) => {
    try {
        dispatch(showSpinnerDialog(fetchingProduct));
        const response = await axiosInstance.get(
            '/products?shopCode=' + shopCode
        );
        const responseJson = await response.data;
        dispatch(hideSpinnerDialog());
        if (checkSuccess(responseJson)) {
            dispatch(fetchProducts('shopCode', responseJson.payload));
        } else {
            dispatch(showToast(errorOccurred, responseJson.status, 'error'));
        }
    } catch (e) {
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
            `/products/${product.code}`,
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
