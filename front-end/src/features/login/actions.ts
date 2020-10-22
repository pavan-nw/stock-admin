import { LOGIN, LoginAction, LOGOUT, LogoutAction } from './types';

export const authenticate = (
    username: string,
    shopCode: string,
    token: string
): LoginAction => {
    return {
        type: LOGIN,
        username,
        shopCode,
        token,
    };
};

export const clearSession = (): LogoutAction => {
    return {
        type: LOGOUT,
    };
};
