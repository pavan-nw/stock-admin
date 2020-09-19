import { CounterActionTypes, SystemState } from './types';
import { ThunkAction } from 'redux-thunk';
import { decrement, increment } from './actions';
import { CommonActionTypes } from '../common/types';
import {
    hideSpinnerDialog,
    hideToast,
    showSpinnerDialog,
    showToast,
} from '../common/actions';

export const incrementIfOdd = (): ThunkAction<
    void,
    SystemState,
    unknown,
    CounterActionTypes | CommonActionTypes
> => async (dispatch, getState) => {
    const { count } = getState();
    console.log('count: ', count);
    if (count.value % 2 === 0) {
        return;
    }
    dispatch(showSpinnerDialog('Loading long long message...'));
    // console.log('fetching todos: ');
    // await fetch('https://jsonplaceholder.typicode.com/todos/1')
    //     .then((response) => response.json())
    //     .then((json) => console.log(json));

    setTimeout(() => {
        dispatch(increment());
        dispatch(hideSpinnerDialog());
    }, 5000);

    dispatch(showToast('Display Summary', 'Count is ' + count.value));
    console.log('count after increment: ', getState());
};

export const decrementIfOdd = (): ThunkAction<
    void,
    SystemState,
    unknown,
    CounterActionTypes | CommonActionTypes
> => async (dispatch, getState) => {
    const { count } = getState();
    console.log('count: ', count);
    if (count.value % 2 === 0) {
        return;
    }
    await dispatch(decrement());
    dispatch(hideToast());
    console.log('count after decrement: ', getState());
};
