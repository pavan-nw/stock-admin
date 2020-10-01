import {
    CommonActionTypes,
    HIDE_SPINNER,
    HIDE_TOAST,
    SHOW_SPINNER,
    SHOW_TOAST,
} from './types';

const initialState = {
    toast: {
        show: false,
        summary: '',
        detail: '',
        severity: 'success',
    },
    spinnerDialog: {
        show: false,
        message: '',
    },
};

export default (state = initialState, action: CommonActionTypes) => {
    switch (action.type) {
        case SHOW_TOAST:
            return {
                ...state,
                toast: {
                    ...state.toast,
                    show: true,
                    summary: action.summary,
                    detail: action.detail,
                    severity: action.severity || 'success',
                },
            };
        case HIDE_TOAST:
            return {
                ...state,
                toast: {
                    ...state.toast,
                    show: false,
                    summary: '',
                    detail: '',
                },
            };
        case SHOW_SPINNER:
            return {
                ...state,
                spinnerDialog: {
                    ...state.spinnerDialog,
                    show: true,
                    message: action.message,
                },
            };
        case HIDE_SPINNER:
            return {
                ...state,
                spinnerDialog: {
                    ...state.spinnerDialog,
                    show: false,
                    message: '',
                },
            };
        default:
            return state;
    }
};
