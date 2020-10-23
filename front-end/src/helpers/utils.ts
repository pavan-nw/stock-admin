import Cookies from 'js-cookie';

export const getErrorMessageToShow: any = (e: any) => {
    if (e.response) {
        console.log('e: ', e.response);
        return e.response.data.payload
            ? e.response.data.payload.errorMessage
            : e.response.statusText;
    } else {
        console.error('e: ', e.toString());
        return e.toString();
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

export const formatDate = (date: string | Date) => {
    // Given date =  2020-10-03T00:00:00.000+00:00

    let formattedDate = new Intl.DateTimeFormat('en-IN', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
    }); // 10/03/2020

    if (typeof date === 'string') {
        return formattedDate.format(new Date(date));
    } else {
        return formattedDate.format(date);
    }
};
