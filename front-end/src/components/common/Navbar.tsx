import React from 'react';
import { NavLink } from 'react-router-dom';
import { Button } from 'primereact/button';
import { Toolbar } from 'primereact/toolbar';
import './navbar.css';
import {
    aboutMenu,
    appName,
    logoutMenu,
    productsMenu,
    stocksMenu,
} from '../../helpers/constants';
import { useDispatch } from 'react-redux';
import { logout } from '../../features/login/loginThunk';

const leftContents = () => (
    <div className="p-d-flex p-jc-between">
        <div className="p-d-flex p-ai-center">
            <Button className="p-mr-2" icon="pi pi-bars" />
            <div className="p-mr-2">
                <NavLink className="link-text" to="/home">
                    <Button className="p-button-text" label={appName} />
                </NavLink>
            </div>
        </div>
    </div>
);

export const Navbar: React.FC = () => {
    const dispatch = useDispatch();
    const onLogout = () => {
        dispatch(logout());
    };
    const rightContents = () => {
        return (
            <div className="p-d-flex p-ai-center">
                <div className="p-mr-2">
                    <NavLink className="link-text" to="/">
                        <Button className="p-button-text" label={stocksMenu} />
                    </NavLink>
                </div>
                <div className="p-mr-2">
                    <NavLink className="link-text" to="/products">
                        <Button
                            className="p-button-text"
                            label={productsMenu}
                        />
                    </NavLink>
                </div>
                <div className="p-mr-2">
                    <NavLink className="link-text" to="/about">
                        <Button className="p-button-text" label={aboutMenu} />
                    </NavLink>
                </div>
                <div className="p-mr-2">
                    <NavLink className="link-text" to="/login">
                        <Button
                            className="p-button-text"
                            label={logoutMenu}
                            onClick={(event) => onLogout()}
                        />
                    </NavLink>
                </div>
            </div>
        );
    };

    return (
        <Toolbar
            className="layout-topbar"
            left={leftContents}
            right={rightContents}
        />
    );
};
