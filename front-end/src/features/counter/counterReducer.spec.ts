import counterReducer from './counterReducer';
import { INCREMENT_COUNTER, DECREMENT_COUNTER } from './types';
import { CounterActionTypes } from './types';

describe('features > counter > counterReducer', () => {
    it(`increments value, if ${INCREMENT_COUNTER} action is provided`, () => {
        const initialState = {
            value: 0,
            show: false,
        };

        const expectedState = {
            value: 1,
            show: true,
        };

        const action: CounterActionTypes = {
            type: INCREMENT_COUNTER,
        };

        expect(counterReducer(initialState, action)).toEqual(expectedState);
    });

    it(`increments value, if ${DECREMENT_COUNTER} action is provided`, () => {
        const initialState = {
            value: 0,
            show: false,
        };

        const expectedState = {
            value: -1,
            show: false,
        };

        const action: CounterActionTypes = {
            type: DECREMENT_COUNTER,
        };

        expect(counterReducer(initialState, action)).toEqual(expectedState);
    });
});
