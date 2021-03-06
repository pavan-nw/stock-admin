import Cookies from 'js-cookie';
import axiosInstance from '../config/axiosConfig';
import { Product } from '../features/stocks/types';

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
    const headers= {
        'content-type': 'application/json',
        'Authorization':'Bearer '+ token
    }
    axiosInstance.defaults.headers= headers
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
    return Cookies.get('token');
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

export const getUniqueProducts = (products : Product []) => {
    return products.filter((product, index) => {
        const foundProduct = products.find(
            (productToBeSearched) => productToBeSearched.name === product.name
        );
        return foundProduct ? products.indexOf(foundProduct) === index : true;
    }); 
}
