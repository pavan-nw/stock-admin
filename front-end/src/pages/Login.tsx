import React, { useEffect } from 'react';
import { useHistory } from 'react-router-dom';
import LoginForm from '../components/login/LoginForm';
import { useSelector } from 'react-redux';
import { getShopCode } from '../features/login/selectors';

export const Login: React.FC = () => {
    const shopCode = useSelector(getShopCode);
    const history = useHistory();

    useEffect(() => {
        if (shopCode) {
            history.push('/home');
        }
    }, [shopCode]);

    return (
        <div className="p-lg-12 p-md-12 p-sm-12">
            <LoginForm />
        </div>
    );
};
