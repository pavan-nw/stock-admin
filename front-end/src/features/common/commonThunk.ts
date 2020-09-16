import { ThunkAction } from 'redux-thunk';
import { showToast, hideToast } from './actions';
import { CommonActionTypes, CommonState } from './types';

export const displayToast = (
    summary: String,
    detail: String
): ThunkAction<void, CommonState, unknown, CommonActionTypes> => async (
    dispatch,
    getState
) => {
    const { common } = getState();
    console.log('show: ', common.toast);
    dispatch(showToast(summary, detail));
};

export const clearToast = (): ThunkAction<
    void,
    CommonState,
    unknown,
    CommonActionTypes
> => async (dispatch, getState) => {
    const { common } = getState();
    console.log('show: ', common.toast);
    dispatch(hideToast());
};
