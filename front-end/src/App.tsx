import React from 'react';
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

const App: React.FC = () => {
    return (
        <BrowserRouter>
            <Navbar />
            <ToastWrapper />
            <SpinnerDialog />
            <div className="p-grid layout-main-content">
                <Switch>
                    <Route path="/" component={Products} exact />
                    <Route path="/home" component={Home} exact />
                    <Route path="/counter" component={Counter} />
                    <Route path="/about" component={About} />
                </Switch>
            </div>
        </BrowserRouter>
    );
};

export default App;
