import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import { Navbar } from './components/Navbar';
import { Home } from './pages/Home';
import Counter from './components/counter';
import { About } from './pages/About';

import 'primereact/resources/themes/saga-green/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import 'primeflex/primeflex.css';
import './app.css';

const App: React.FC = () => {
    return (
        <BrowserRouter>
            <Navbar />
            <div className="p-grid layout-main-content">
                <Switch>
                    <Route path="/" component={Home} exact />
                    <Route path="/counter" component={Counter} />
                    <Route path="/about" component={About} />
                </Switch>
            </div>
        </BrowserRouter>
    );
};

export default App;
