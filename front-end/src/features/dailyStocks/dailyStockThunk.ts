import { ThunkAction } from 'redux-thunk';
import {    
    fetchStocks
} from './actions';
import { StockState,StockActionTypes } from './types';
import {
    hideSpinnerDialog,
    showSpinnerDialog,
    showToast,
} from '../common/actions';
import { CommonActionTypes } from '../common/types';
import { checkSuccess, getErrorMessageToShow } from '../../helpers/utils';
import axiosInstance from '../../config/axiosConfig';
import {   
    errorOccurred,    
    fetchingStocks
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
            dispatch(fetchStocks('shopCode', responseJson.payload));
        } else {
            dispatch(showToast(errorOccurred, responseJson.status, 'error'));
        }
    } catch (e) {        
        dispatch(hideSpinnerDialog());
        dispatch(showToast(errorOccurred, getErrorMessageToShow(e), 'error'));
    }
};