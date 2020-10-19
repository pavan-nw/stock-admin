import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { Button } from 'primereact/button';
import { Card } from 'primereact/card';
import {
    loginLabel,
    passwordLabel,
    passwordPlaceHolder,
    shopLabel,
    shopPlaceHolder,
    userNameLabel,
    usernamePlaceHolder,
} from '../../helpers/constants';
import { InputText } from 'primereact/inputtext';
import { Dropdown } from 'primereact/dropdown';
import { login } from '../../features/login/loginThunk';

const LoginForm: React.FC = () => {
    const dispatch = useDispatch();

    const [shop, setShop] = useState('');
    const [password, setPassword] = useState('');
    const [userName, setUserName] = useState('');

    const allShops = ['Seeds Center', 'Seeds Corner'];

    const onLogin = () => {
        dispatch(login(userName, password, shop));
    };

    return (
        <div className="p-lg-12 p-md-12 p-sm-12 layout-stretched p-d-flex">
            <div className="p-lg-4 p-md-4 p-sm-12 p-lg-offset-4 p-md-offset-4 p-as-center">
                <Card title={loginLabel}>
                    <div className="p-fluid">
                        <div className="p-field p-grid">
                            <label
                                htmlFor="userName"
                                className="p-col-12 p-md-3"
                            >
                                {userNameLabel}
                            </label>
                            <div className="p-col-12 p-md-9">
                                <InputText
                                    id="userName"
                                    type="text"
                                    value={userName}
                                    onChange={(event: any) =>
                                        setUserName(event.target.value)
                                    }
                                    placeholder={usernamePlaceHolder}
                                />
                            </div>
                        </div>
                        <div className="p-field p-grid">
                            <label
                                htmlFor="password"
                                className="p-col-12 p-md-3"
                            >
                                {passwordLabel}
                            </label>
                            <div className="p-col-12 p-md-9">
                                <InputText
                                    id="password"
                                    type="password"
                                    value={password}
                                    onChange={(event: any) =>
                                        setPassword(event.target.value)
                                    }
                                    placeholder={passwordPlaceHolder}
                                />
                            </div>
                        </div>
                        <div className="p-field p-grid">
                            <label htmlFor="shop" className="p-col-12 p-md-3">
                                {shopLabel}
                            </label>
                            <div className="p-col-12 p-md-9">
                                <Dropdown
                                    id="shop"
                                    value={shop}
                                    options={allShops}
                                    optionLabel={name}
                                    onChange={(e) => setShop(e.value)}
                                    placeholder={shopPlaceHolder}
                                />
                            </div>
                        </div>
                        <div className="p-grid">
                            <div className="p-lg-6 p-md-6 p-sm-12 p-lg-offset-3 p-md-offset-3 p-mt-4">
                                <Button
                                    type="button"
                                    className="p-button-raised p-mr-2"
                                    label="Login"
                                    onClick={(event) => onLogin()}
                                />
                            </div>
                        </div>
                    </div>
                </Card>
            </div>
        </div>
    );
};

export default LoginForm;
