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
import axios from 'axios';
import { checkSuccess, getErrorMessageToShow } from '../../helpers/utils';
import axiosInstance from '../../config/axiosConfig';
import {
    creatingProduct,
    deletingProduct,
    errorOccurred,
    fetchingProduct,
    updatingProduct,
} from '../../helpers/constants';

export const getProducts = (): ThunkAction<
    void,
    ProductState,
    unknown,
    ProductActionTypes | CommonActionTypes
> => async (dispatch, getState) => {
    try {
        dispatch(showSpinnerDialog(fetchingProduct));

        const response = await axiosInstance.get('/products');

        console.log(response);
        const responseJson = await response.data;
        console.log('responseJson: ', responseJson);
        dispatch(hideSpinnerDialog());
        if (checkSuccess(responseJson)) {
            dispatch(fetchProducts('shopCode', responseJson.payload));
        } else {
            dispatch(showToast(errorOccurred, responseJson.status));
        }
    } catch (e) {
        dispatch(hideSpinnerDialog());
        dispatch(showToast(errorOccurred, getErrorMessageToShow(e)));
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
        console.log('createProductRequest: ', createProductRequest);
        const response = await axiosInstance.post(
            '/products',
            createProductRequest
        );

        console.log(response);
        const responseJson = await response.data;
        console.log('responseJson: ', responseJson);
        dispatch(hideSpinnerDialog());
        if (checkSuccess(responseJson)) {
            dispatch(createProduct(responseJson.payload));
        } else {
            dispatch(showToast(errorOccurred, responseJson.status));
        }
    } catch (e) {
        dispatch(hideSpinnerDialog());
        dispatch(showToast(errorOccurred, getErrorMessageToShow(e)));
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

        console.log(response);
        const responseJson = await response.data;
        console.log('responseJson: ', responseJson);
        dispatch(hideSpinnerDialog());
        if (checkSuccess(responseJson)) {
            dispatch(updateProduct(responseJson.payload));
            dispatch(toggleShowEditDialog());
        } else {
            dispatch(showToast(errorOccurred, responseJson.status));
        }
    } catch (e) {
        dispatch(hideSpinnerDialog());
        dispatch(showToast(errorOccurred, getErrorMessageToShow(e)));
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

        console.log(response);
        const responseJson = await response.data;
        console.log('responseJson: ', responseJson);
        dispatch(hideSpinnerDialog());
        if (checkSuccess(responseJson)) {
            dispatch(deleteProduct(responseJson.payload));
        } else {
            dispatch(showToast(errorOccurred, responseJson.status));
        }
    } catch (e) {
        dispatch(hideSpinnerDialog());
        dispatch(showToast(errorOccurred, getErrorMessageToShow(e)));
    }
};
