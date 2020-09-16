import { CommonState } from './types';

export const getShowToast = (state: CommonState) => state.common.toast;
export const getShowSpinnerDialog = (state: CommonState) =>
    state.common.spinnerDialog;
