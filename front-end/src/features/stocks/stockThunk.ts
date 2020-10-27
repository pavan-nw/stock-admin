import { ThunkAction } from 'redux-thunk';
import { fetchStocks } from './actions';
import {
    StockState,
    StockActionTypes,
    CreateStockRequest,
    SearchStockRequest,
} from './types';
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
    stockAdded,
    fetchingStocks,
    creatingStocks,
} from '../../helpers/constants';

export const getStocks = (
    shopCode: string
): ThunkAction<
    void,
    StockState,
    unknown,
    StockActionTypes | CommonActionTypes
> => async (dispatch, getState) => {
    try {
        dispatch(showSpinnerDialog(fetchingStocks));
        const response = await axiosInstance.get(
            '/stocks?shopCode=' + shopCode
        );
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

export const addStock = (
    shopCode: string
): ThunkAction<
    void,
    StockState,
    unknown,
    StockActionTypes | CommonActionTypes
> => async (dispatch, getState) => {
    try {
        dispatch(showSpinnerDialog(creatingStocks));
        const { currentStock } = getState().stockState;
        const stockRequest: CreateStockRequest = {
            productName: currentStock.product.name,
            packaging: currentStock.packaging.name,
            stockDate: new Date(
                Date.UTC(
                    currentStock.stockDate.getFullYear(),
                    currentStock.stockDate.getMonth(),
                    currentStock.stockDate.getDate()
                )
            ),
            openingStock: currentStock.openingStocks,
            closingStock: currentStock.closingStocks,
            shopCode,
            type: 'stock-request',
        };
        const response = await axiosInstance.post('/stocks', stockRequest);
        const responseJson = await response.data;
        dispatch(hideSpinnerDialog());
        if (checkSuccess(responseJson)) {
            dispatch(showToast(stockAdded, responseJson.status));
        } else {
            dispatch(showToast(errorOccurred, responseJson.status, 'error'));
        }
    } catch (e) {
        dispatch(hideSpinnerDialog());
        dispatch(showToast(errorOccurred, getErrorMessageToShow(e), 'error'));
    }
};

export const searchStock = (
    shopCode: string
): ThunkAction<
    void,
    StockState,
    unknown,
    StockActionTypes | CommonActionTypes
> => async (dispatch, getState) => {
    try {
        dispatch(showSpinnerDialog(creatingStocks));
        const { currentStock } = getState().stockState;
        const stockRequest: SearchStockRequest = {
            productName:
                currentStock.product != null && currentStock.product.name !== ''
                    ? currentStock.product.name
                    : null,
            packaging:
                currentStock.packaging != null &&
                currentStock.packaging.name !== ''
                    ? currentStock.packaging.name
                    : null,
            stockDate: new Date(
                Date.UTC(
                    currentStock.stockDate.getFullYear(),
                    currentStock.stockDate.getMonth(),
                    currentStock.stockDate.getDate()
                )
            ),
            shopCode,
            type: 'stock-request',
        };
        const response = await axiosInstance.post(
            '/stocks/search',
            stockRequest
        );
        const responseJson = await response.data;
        dispatch(hideSpinnerDialog());
        if (checkSuccess(responseJson)) {
            dispatch(fetchStocks(shopCode, responseJson.payload));
        } else {
            dispatch(showToast(errorOccurred, responseJson.status, 'error'));
        }
    } catch (e) {
        dispatch(hideSpinnerDialog());
        dispatch(showToast(errorOccurred, getErrorMessageToShow(e), 'error'));
    }
};
