import React, { useEffect, useRef } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { getCountValue } from '../../features/counter/selectors';
import { Button } from 'primereact/button';
import { Card } from 'primereact/card';
import './counter.css';
import {
    decrementIfOdd,
    incrementIfOdd,
} from '../../features/counter/counterThunk';
import { decrement, increment } from '../../features/counter/actions';

const Counter: React.FC = () => {
    const count = useSelector(getCountValue);
    const dispatch = useDispatch();
    const footer = (
        <div>
            <Button
                className="p-button-raised p-mr-2 p-mb-2"
                onClick={() => dispatch(decrement())}
                label="Decrement"
            />
            <Button
                className="p-button-raised p-mr-2 p-mb-2"
                onClick={() => dispatch(increment())}
                label="Increment"
            />
            <Button
                className="p-button-raised p-mr-2 p-mb-2"
                onClick={() => {
                    dispatch(incrementIfOdd());
                }}
                label="Increment If Odd"
            />
            <Button
                className="p-button-raised p-mb-2"
                onClick={() => {
                    dispatch(decrementIfOdd());
                }}
                label="Decrement If Odd"
            />
        </div>
    );
    return (
        <div className="p-col-12">
            <div className="p-col-6 p-offset-3">
                <Card title="Counter" footer={footer}>
                    <h4>
                        Counter: <strong>{count}</strong>
                    </h4>
                    <p>
                        Here you can increment and decrement counter value using
                        buttons below. All the state updates are performed via
                        redux actions.
                    </p>
                </Card>
            </div>
        </div>
    );
};

export default Counter;
