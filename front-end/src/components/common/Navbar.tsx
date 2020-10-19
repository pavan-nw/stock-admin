import React from 'react';
import { NavLink } from 'react-router-dom';
import { Button } from 'primereact/button';
import { Toolbar } from 'primereact/toolbar';
import './navbar.css';
import {
    aboutMenu,
    appName,
    dailyStocksMenu,
    logoutMenu,
    productsMenu,
    stocksMenu,
} from '../../helpers/constants';

const leftContents = () => (
    <div className="p-d-flex p-jc-between">
        <div className="p-d-flex p-ai-center">
            <Button className="p-mr-2" icon="pi pi-bars" />
            <div className="p-mr-2 layout-topbar-title">{appName}</div>
        </div>
    </div>
);

const rightContents = () => (
    <>
        <div className="p-d-flex p-ai-center">
            {/*<div className="p-mr-2">*/}
            {/*    <NavLink className="link-text" to="/home">*/}
            {/*        <Button className="p-button-text" label="Home" />*/}
            {/*    </NavLink>*/}
            {/*</div>*/}
            <div className="p-mr-2">
                <NavLink className="link-text" to="/">
                    <Button className="p-button-text" label={productsMenu} />
                </NavLink>
            </div>
            <div className="p-mr-2">
                <NavLink className="link-text" to="/daily-stocks">
                    <Button className="p-button-text" label={dailyStocksMenu} />
                </NavLink>
            </div>
            <div className="p-mr-2">
                <NavLink className="link-text" to="/counter">
                    <Button className="p-button-text" label={stocksMenu} />
                </NavLink>
            </div>
            <div className="p-mr-2">
                <NavLink className="link-text" to="/about">
                    <Button className="p-button-text" label={aboutMenu} />
                </NavLink>
            </div>
            <div className="p-mr-2">
                <NavLink className="link-text" to="/logout">
                    <Button className="p-button-text" label={logoutMenu} />
                </NavLink>
            </div>
        </div>
    </>
);
export const Navbar: React.FC = () => (
    <Toolbar
        className="layout-topbar"
        left={leftContents}
        right={rightContents}
    />
);
