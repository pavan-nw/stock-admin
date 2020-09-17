export const SHOW_TOAST = 'SHOW_TOAST';
export const HIDE_TOAST = 'HIDE_TOAST';
export const SHOW_SPINNER = 'SHOW_SPINNER';
export const HIDE_SPINNER = 'HIDE_SPINNER';

export interface ShowToastAction {
    type: typeof SHOW_TOAST;
    summary: String;
    detail: String;
}

export interface HideToatAction {
    type: typeof HIDE_TOAST;
}

export interface ShowSpinnerAction {
    type: typeof SHOW_SPINNER;
    message: String;
}

export interface HideSpinnerAction {
    type: typeof HIDE_SPINNER;
}

export type CommonActionTypes =
    | ShowToastAction
    | HideToatAction
    | ShowSpinnerAction
    | HideSpinnerAction;

export interface ToastData {
    show: Boolean;
    summary: String;
    detail: String;
}

export interface SpinnerDialogData {
    show: boolean;
    message: String;
}

export interface CommonState {
    common: {
        toast: ToastData;
        spinnerDialog: SpinnerDialogData;
    };
}
