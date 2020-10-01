export const INCREMENT_COUNTER = 'INCREMENT_COUNTER';
export const DECREMENT_COUNTER = 'DECREMENT_COUNTER';

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
    counterState: {
        value: number;
    };
}
