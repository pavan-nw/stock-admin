import { Shop, ShopActionTypes, ShopState } from './types';
import { ThunkAction } from 'redux-thunk';
import { CommonActionTypes } from '../common/types';
import {
    hideSpinnerDialog,
    showSpinnerDialog,
    showToast,
} from '../common/actions';
import { errorOccurred } from '../../helpers/constants';
import axiosInstance from '../../config/axiosConfig';
import { checkSuccess, getErrorMessageToShow } from '../../helpers/utils';
import { fetchShops } from './actions';

export const getShops = (): ThunkAction<
    void,
    ShopState,
    unknown,
    ShopActionTypes | CommonActionTypes
> => async (dispatch, getState) => {
    try {
        dispatch(showSpinnerDialog('Fetching shops...'));
        const response = await axiosInstance.get('/shops');
        const responseJson = await response.data;
        if (checkSuccess(responseJson)) {
            dispatch(fetchShops(responseJson.payload));
            dispatch(hideSpinnerDialog());
        } else {
            dispatch(showToast(errorOccurred, responseJson.status, 'error'));
            dispatch(hideSpinnerDialog());
        }
    } catch (e) {
        dispatch(hideSpinnerDialog());
        dispatch(showToast(errorOccurred, getErrorMessageToShow(e), 'error'));
    }
};
