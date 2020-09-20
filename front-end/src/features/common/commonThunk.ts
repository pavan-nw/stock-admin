import { ThunkAction } from 'redux-thunk';
import { showToast, hideToast } from './actions';
import { CommonActionTypes, CommonState } from './types';

export const displayToast = (
    summary: string,
    detail: string
): ThunkAction<void, CommonState, unknown, CommonActionTypes> => async (
    dispatch,
    getState
) => {
    const { commonState } = getState();
    console.log('show: ', commonState.toast);
    dispatch(showToast(summary, detail));
};

export const clearToast = (): ThunkAction<
    void,
    CommonState,
    unknown,
    CommonActionTypes
> => async (dispatch, getState) => {
    const { commonState } = getState();
    console.log('show: ', commonState.toast);
    dispatch(hideToast());
};
