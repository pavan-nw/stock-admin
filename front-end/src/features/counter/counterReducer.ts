import { INCREMENT_COUNTER, DECREMENT_COUNTER } from './actionTypes';
import { CounterActionTypes } from './types';

const initialState = {
    value: 0,
    show: false,
};

export default (state = initialState, action: CounterActionTypes) => {
    switch (action.type) {
        case INCREMENT_COUNTER:
            return { ...state, value: state.value + 1, show: true };
        case DECREMENT_COUNTER:
            return { ...state, value: state.value - 1, show: false };
        default:
            return state;
    }
};
