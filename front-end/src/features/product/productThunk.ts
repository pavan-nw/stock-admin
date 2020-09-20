import { ThunkAction } from 'redux-thunk';
import { fetchProducts } from './actions';
import { ProductActionTypes, ProductState } from './types';
import {
    hideSpinnerDialog,
    showSpinnerDialog,
    showToast,
} from '../common/actions';
import { CommonActionTypes } from '../common/types';
import axios from 'axios';

export const getProducts = (): ThunkAction<
    void,
    ProductState,
    unknown,
    ProductActionTypes | CommonActionTypes
> => async (dispatch, getState) => {
    const { productState } = getState();
    try {
        dispatch(showSpinnerDialog('Fetching products...'));
        const response = await axios.get(
            'https://69a1fe93-bc85-4be9-903e-4f0cf01e3aaf.mock.pstmn.io/products',
            {
                headers: {
                    'Content-Type': 'application/json',
                },
            }
        );

        console.log(response);
        const responseJson = await response.data;
        console.log('responseJson: ', responseJson);
        dispatch(hideSpinnerDialog());
        if (responseJson.status === 'Success') {
            dispatch(fetchProducts('shopCode', responseJson.payload));
        } else {
            dispatch(showToast('Error Occurred', responseJson.status));
        }
    } catch (e) {
        dispatch(hideSpinnerDialog());
        if (e.response) {
            console.error('e: ', e.response);
            const errorMessage = e.response.data.payload
                ? e.response.data.payload.errorMessage
                : e.response.data.error.message;
            dispatch(showToast('Error Occurred', errorMessage));
        } else {
            console.error('e: ', e.toString());
            showToast('Error Occurred', e.response.data.payload.errorMessage);
        }
    }
};
