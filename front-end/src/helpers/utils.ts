import Cookies from 'js-cookie';

export const getErrorMessageToShow: any = (e: any) => {
    if (e.response) {
        console.error('e: ', e.response);
        return e.response.data.payload
            ? e.response.data.payload.errorMessage
            : e.response.data.error.message;
    } else {
        console.error('e: ', e.toString());
        return e.response.data.payload.errorMessage;
    }
};

export const checkSuccess = ({ status }: any) => status === 'Success';

export const setSessionInfo = (
    token: string,
    username: string,
    shopCode: string
): void => {
    Cookies.set('token', token);
    Cookies.set('username', username);
    Cookies.set('shopCode', shopCode);
};

export const clearSessionInfo = (): void => {
    Cookies.remove('token');
    Cookies.remove('username');
    Cookies.remove('shopCode');
};

export const isLoggedIn = (): boolean => {
    return !!(
        getTokenFromCookie() &&
        getUsernameFromCookie() &&
        getShopCodeFromCookie()
    );
};

export const getTokenFromCookie = (): string | undefined => {
    return Cookies.get('username');
};

export const getUsernameFromCookie = (): string | undefined => {
    return Cookies.get('username');
};

export const getShopCodeFromCookie = (): string | undefined => {
    return Cookies.get('shopCode');
};
