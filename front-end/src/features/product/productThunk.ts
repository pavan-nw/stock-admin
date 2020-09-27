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

export const getProducts = (): ThunkAction<
    void,
    ProductState,
    unknown,
    ProductActionTypes | CommonActionTypes
> => async (dispatch, getState) => {
    try {
        dispatch(showSpinnerDialog('Fetching products...'));
        const response = await axios.get(
            'https://69a1fe93-bc85-4be9-903e-4f0cf01e3aaf.mock.pstmn.io/products'
        );

        console.log(response);
        const responseJson = await response.data;
        console.log('responseJson: ', responseJson);
        dispatch(hideSpinnerDialog());
        if (checkSuccess(responseJson)) {
            dispatch(fetchProducts('shopCode', responseJson.payload));
        } else {
            dispatch(showToast('Error Occurred', responseJson.status));
        }
    } catch (e) {
        dispatch(hideSpinnerDialog());
        dispatch(showToast('Error Occurred', getErrorMessageToShow(e)));
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
        dispatch(showSpinnerDialog('Creating product...'));
        const createProductRequest = {
            type: 'product',
            product,
        };
        console.log('createProductRequest: ', createProductRequest);
        const response = await axios.post(
            'https://69a1fe93-bc85-4be9-903e-4f0cf01e3aaf.mock.pstmn.io/products',
            createProductRequest
        );

        console.log(response);
        const responseJson = await response.data;
        console.log('responseJson: ', responseJson);
        dispatch(hideSpinnerDialog());
        if (checkSuccess(responseJson)) {
            dispatch(createProduct(responseJson.payload));
        } else {
            dispatch(showToast('Error Occurred', responseJson.status));
        }
    } catch (e) {
        dispatch(hideSpinnerDialog());
        dispatch(showToast('Error Occurred', getErrorMessageToShow(e)));
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
        dispatch(showSpinnerDialog('Updating product...'));
        const editProductRequest = {
            type: 'product',
            product,
        };
        const response = await axios.put(
            `https://69a1fe93-bc85-4be9-903e-4f0cf01e3aaf.mock.pstmn.io/products/${product.id}`,
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
            dispatch(showToast('Error Occurred', responseJson.status));
        }
    } catch (e) {
        dispatch(hideSpinnerDialog());
        dispatch(showToast('Error Occurred', getErrorMessageToShow(e)));
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
        dispatch(showSpinnerDialog('Deleting product...'));
        const response = await axios.delete(
            `https://69a1fe93-bc85-4be9-903e-4f0cf01e3aaf.mock.pstmn.io/products/${productId}`
        );

        console.log(response);
        const responseJson = await response.data;
        console.log('responseJson: ', responseJson);
        dispatch(hideSpinnerDialog());
        if (checkSuccess(responseJson)) {
            dispatch(deleteProduct(responseJson.payload));
        } else {
            dispatch(showToast('Error Occurred', responseJson.status));
        }
    } catch (e) {
        dispatch(hideSpinnerDialog());
        dispatch(showToast('Error Occurred', getErrorMessageToShow(e)));
    }
};
