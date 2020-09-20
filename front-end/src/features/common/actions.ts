import {
    HIDE_SPINNER,
    HIDE_TOAST,
    HideSpinnerAction,
    HideToatAction,
    SHOW_SPINNER,
    SHOW_TOAST,
    ShowSpinnerAction,
    ShowToastAction,
} from './types';

export const showToast = (summary: string, detail: string): ShowToastAction => {
    return {
        type: SHOW_TOAST,
        summary,
        detail,
    };
};

export const hideToast = (): HideToatAction => {
    return {
        type: HIDE_TOAST,
    };
};

export const showSpinnerDialog = (message: string): ShowSpinnerAction => {
    return {
        type: SHOW_SPINNER,
        message,
    };
};

export const hideSpinnerDialog = (): HideSpinnerAction => {
    return {
        type: HIDE_SPINNER,
    };
};
