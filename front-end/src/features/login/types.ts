export const LOGIN = 'LOGIN';
export const LOGOUT = 'LOGOUT';

export interface LoginAction {
    type: typeof LOGIN;
    username: string;
    shopCode: string;
    token: string;
}

export interface LogoutAction {
    type: typeof LOGOUT;
}

export type LoginActionTypes = LoginAction | LogoutAction;

export interface SessionState {
    sessionState: {
        username: string;
        shopCode: string;
    };
}
