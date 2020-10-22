import { LOGIN, LoginActionTypes, LOGOUT } from './types';
import Cookies from 'js-cookie';
import { clearSessionInfo, setSessionInfo } from '../../helpers/utils';

const initialState = {
    username: '',
    shopCode: '',
};

export default (state = initialState, action: LoginActionTypes) => {
    switch (action.type) {
        case LOGIN:
            setSessionInfo(action.token, action.username, action.shopCode);
            return {
                ...state,
                username: action.username,
                shopCode: action.shopCode,
            };
        case LOGOUT:
            clearSessionInfo();
            return initialState;
        default:
            return state;
    }
};
