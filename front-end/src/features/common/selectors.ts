import { CommonState } from './types';

export const getShowToast = (state: CommonState) => state.commonState.toast;
export const getShowSpinnerDialog = (state: CommonState) =>
    state.commonState.spinnerDialog;
