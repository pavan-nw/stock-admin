import { ThunkAction } from 'redux-thunk';
import { showToast, hideToast } from './actions';
import { CommonActionTypes, CommonState, ToastSeverity } from './types';

export const displayToast = (
    summary: string,
    detail: string,
    severity?: ToastSeverity
): ThunkAction<void, CommonState, unknown, CommonActionTypes> => async (
    dispatch,
    getState
) => {
    dispatch(showToast(summary, detail, severity));
};

export const clearToast = (): ThunkAction<
    void,
    CommonState,
    unknown,
    CommonActionTypes
> => async (dispatch, getState) => {
    dispatch(hideToast());
};
