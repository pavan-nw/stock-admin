import { applyMiddleware, combineReducers, compose, createStore } from 'redux';
import thunk from 'redux-thunk';
import { devToolsEnhancer } from 'redux-devtools-extension';
import CounterReducer from './features/counter/counterReducer';
import CommonReducer from './features/common/commonReducer';
import ProductReducer from './features/product/productReducer';

/* Create root reducer, containing all features of the application */
const rootReducer = combineReducers({
    counterState: CounterReducer,
    commonState: CommonReducer,
    productState: ProductReducer,
});

const store = createStore(
    rootReducer,
    /* preloadedState, */
    compose(applyMiddleware(thunk), devToolsEnhancer({}))
);

export default store;
