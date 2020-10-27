import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { RouteProps } from 'react-router';
import { isLoggedIn } from '../../helpers/utils';

export const PrivateRoute: React.FC<RouteProps> = ({
    path,
    component,
    ...rest
}) => {
    return isLoggedIn() ? (
        <Route path={path} component={component} {...rest} />
    ) : (
        <Redirect to="/login" />
    );
};
