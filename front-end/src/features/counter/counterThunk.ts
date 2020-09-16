import { DECREMENT_COUNTER, INCREMENT_COUNTER } from './actionTypes';
import {
    CounterActionTypes,
    DecrementCounterAction,
    IncrementCounterAction,
    SystemState,
} from './types';
import { ThunkAction } from 'redux-thunk';

const increment = (): IncrementCounterAction => {
    return {
        type: INCREMENT_COUNTER,
    };
};

const decrement = (): DecrementCounterAction => {
    return {
        type: DECREMENT_COUNTER,
    };
};

export const incrementIfOdd = (): ThunkAction<
    void,
    SystemState,
    unknown,
    CounterActionTypes
> => async (dispatch, getState) => {
    const { count } = getState();
    console.log('count: ', count);
    if (count.value % 2 === 0) {
        return;
    }
    // console.log('fetching todos: ')
    // await fetch('https://jsonplaceholder.typicode.com/todos/1')
    //   .then((response) => response.json())
    //   .then((json) => console.log(json))
    await dispatch(increment());
    console.log('count after increment: ', getState());
};

export const decrementIfOdd = (): ThunkAction<
    void,
    SystemState,
    unknown,
    CounterActionTypes
> => async (dispatch, getState) => {
    const { count } = getState();
    console.log('count: ', count);
    if (count.value % 2 === 0) {
        return;
    }
    await dispatch(decrement());
    console.log('count after decrement: ', getState());
};
