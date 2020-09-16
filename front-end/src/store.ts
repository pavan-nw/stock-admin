import { applyMiddleware, combineReducers, compose, createStore } from 'redux';
import thunk from 'redux-thunk';
import { devToolsEnhancer } from 'redux-devtools-extension';
import { CounterReducer } from './features/counter';

/* Create root reducer, containing all features of the application */
const rootReducer = combineReducers({
    count: CounterReducer,
});

const store = createStore(
    rootReducer,
    /* preloadedState, */
    compose(applyMiddleware(thunk), devToolsEnhancer({}))
);

export default store;
