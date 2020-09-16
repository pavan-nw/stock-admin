import { CounterActionTypes, SystemState } from './types';
import { ThunkAction } from 'redux-thunk';
import { decrement, increment } from './actions';

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
