import {
    DecrementCounterAction,
    IncrementCounterAction,
    DECREMENT_COUNTER,
    INCREMENT_COUNTER,
} from './types';

export const increment = (): IncrementCounterAction => {
    return {
        type: INCREMENT_COUNTER,
    };
};

export const decrement = (): DecrementCounterAction => {
    return {
        type: DECREMENT_COUNTER,
    };
};
