import { INCREMENT_COUNTER, DECREMENT_COUNTER } from './actionTypes';

export interface IncrementCounterAction {
    type: typeof INCREMENT_COUNTER;
}

export interface DecrementCounterAction {
    type: typeof DECREMENT_COUNTER;
}

export type CounterActionTypes =
    | IncrementCounterAction
    | DecrementCounterAction;

export interface SystemState {
    count: {
        value: number;
        show: false;
    };
}
