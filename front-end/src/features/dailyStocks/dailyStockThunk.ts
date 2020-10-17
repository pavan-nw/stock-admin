import { ThunkAction } from 'redux-thunk';
import {    
    fetchStocks
} from './actions';
import { StockState,StockActionTypes, StockDetails,CreateStockRequest } from './types';
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
    fetchingStocks,
    creatingStocks,
    packaging
} from '../../helpers/constants';

export const getStocks = (): ThunkAction<
    void,
    StockState,
    unknown,
    StockActionTypes | CommonActionTypes
> => async (dispatch, getState) => {
    try {
        dispatch(showSpinnerDialog(fetchingStocks));
        const response = await axiosInstance.get('/stocks');
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

export const addStock = (): ThunkAction<
    void,
    StockState,
    unknown,
    StockActionTypes | CommonActionTypes
> => async (dispatch, getState) => {
    try {
        dispatch(showSpinnerDialog(creatingStocks));
        const {currentStock} =  getState().stockState;
        const stockRequest : CreateStockRequest = {
            productName :currentStock.product.name,
            packaging : currentStock.packaging.name,
            stockDate:currentStock.stockDate,
            openingStock:currentStock.openingStocks,
            closingStock:currentStock.closingStocks,
            type:"stock-request"
        };
        const response = await axiosInstance.post(
            '/stocks',
            stockRequest
        );
        const responseJson = await response.data;
        dispatch(hideSpinnerDialog());
        if (checkSuccess(responseJson)) {
            //dispatch(createProduct(responseJson.payload));
            showToast("Successfully Added", responseJson.status, 'success')
        } else {
            dispatch(showToast(errorOccurred, responseJson.status, 'error'));
        }
    } catch (e) {
        dispatch(hideSpinnerDialog());
        dispatch(showToast(errorOccurred, getErrorMessageToShow(e), 'error'));
    }
};