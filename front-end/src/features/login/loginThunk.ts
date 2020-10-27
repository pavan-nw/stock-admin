import { LoginActionTypes, SessionState } from './types';
import { ThunkAction } from 'redux-thunk';
import { CommonActionTypes } from '../common/types';
import {
    hideSpinnerDialog,
    showSpinnerDialog,
    showToast,
} from '../common/actions';
import {
    authenticating,
    errorOccurred,
    loggingOut,
} from '../../helpers/constants';
import axiosInstance from '../../config/axiosConfig';
import { checkSuccess, getErrorMessageToShow } from '../../helpers/utils';
import { createProduct } from '../products/actions';
import { authenticate, clearSession } from './actions';

export const login = (
    username: string,
    password: string,
    shopCode: string
): ThunkAction<
    void,
    SessionState,
    unknown,
    LoginActionTypes | CommonActionTypes
> => async (dispatch, getState) => {
    try {
        dispatch(showSpinnerDialog(authenticating));
        const loginRequest = {
            type: 'login',
            username,
            password,
            shopCode,
        };
        const response = await axiosInstance.post('/login', loginRequest);
        const responseJson = await response.data;
        if (checkSuccess(responseJson)) {
            // responseJson.payload.token to be handled
            console.log('token: ', responseJson.payload.token);
            dispatch(
                authenticate(username, shopCode, responseJson.payload.token)
            );
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

export const logout = (): ThunkAction<
    void,
    SessionState,
    unknown,
    LoginActionTypes | CommonActionTypes
> => async (dispatch, getState) => {
    dispatch(showSpinnerDialog(loggingOut));
    dispatch(clearSession());
    dispatch(hideSpinnerDialog());
};
