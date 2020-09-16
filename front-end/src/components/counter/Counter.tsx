import React, { useEffect, useRef } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { actionTypes, selectors } from '../../features/counter';
import { Button } from 'primereact/button';
import { Card } from 'primereact/card';
import './counter.css';
import {
    decrementIfOdd,
    incrementIfOdd,
} from '../../features/counter/counterThunk';
import { Toast, ToastMessage } from 'primereact/toast';

const Counter: React.FC = () => {
    const count = useSelector(selectors.getCountValue);
    const show = useSelector(selectors.getShow);
    const toast = useRef<Toast>(null);
    const toastMessage: ToastMessage = {
        severity: 'error',
        detail: 'Count is ' + count,
        summary: 'Operation Completed',
    };
    const dispatch = useDispatch();

    useEffect(() => {
        if (show) {
            // @ts-ignore
            toast.current.show(toastMessage);
        }
    });

    const footer = (
        <div>
            <Button
                className="p-button-raised p-mr-2 p-mb-2"
                onClick={() =>
                    dispatch({ type: actionTypes.DECREMENT_COUNTER })
                }
                label="Decrement"
            />
            <Button
                className="p-button-raised p-mr-2 p-mb-2"
                onClick={() =>
                    dispatch({ type: actionTypes.INCREMENT_COUNTER })
                }
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
            <Toast ref={toast} />
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
