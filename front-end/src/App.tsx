import React, { useEffect } from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import { Navbar } from './components/common/Navbar';
import { Home } from './pages/Home';
import Counter from './components/counter/Counter';
import { About } from './pages/About';
import 'primereact/resources/themes/saga-green/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import 'primeflex/primeflex.css';
import './app.css';
import { ToastWrapper } from './components/common/ToastWrapper';
import { SpinnerDialog } from './components/common/SpinnerDialog';
import { Products } from './pages/Products';
import { Stocks } from './pages/Stocks';
import { Login } from './pages/Login';
import { PrivateRoute } from './components/common/PrivateRoute';
import { useDispatch } from 'react-redux';
import { authenticate } from './features/login/actions';
import {
    getShopCodeFromCookie,
    getTokenFromCookie,
    getUsernameFromCookie,
} from './helpers/utils';

const App: React.FC = () => {
    const dispatch = useDispatch();

    useEffect(() => {
        const token = getTokenFromCookie();
        const username = getUsernameFromCookie();
        const shopCode = getShopCodeFromCookie();

        if (username && shopCode && token) {
            dispatch(authenticate(username, shopCode, token));
        }
    }, []);

    return (
        <BrowserRouter>
            <Navbar />
            <ToastWrapper />
            <SpinnerDialog />
            <div className="p-grid layout-main-content layout-stretched">
                <Switch>
                    <PrivateRoute path="/" component={Stocks} exact />
                    <PrivateRoute path="/stocks" component={Stocks} exact />
                    <PrivateRoute path="/products" component={Products} exact />
                    <PrivateRoute path="/home" component={Home} exact />
                    <Route path="/counter" component={Counter} exact />
                    <Route path="/login" component={Login} exact />
                    <Route path="/about" component={About} exact />
                    <Route path="*" component={Login} exact />
                </Switch>
            </div>
        </BrowserRouter>
    );
};

export default App;
