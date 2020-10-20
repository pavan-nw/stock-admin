import React from 'react';
import { Button } from 'primereact/button';
import { saveLabel, clearLabel } from '../../helpers/constants';
import { useDispatch } from 'react-redux';
import { clearCurrentStock } from '../../features/stocks/actions';
import { addStock } from '../../features/stocks/stockThunk';

export const StocksFormFooter: React.FC = (props) => {
    const dispatch = useDispatch();
    const onSave = () => {
        dispatch(addStock());
    };

    const onClear = () => {
        dispatch(clearCurrentStock());
    };

    return (
        <div>
            <div className="p-fluid">
                {props.children}
                <div className="p-grid p-mt-lg-4 p-mt-md-2 p-mt-sm-1">
                    <div className="p-lg-4 p-md-12 p-sm-12 p-lg-offset-4">
                        <div className="p-grid">
                            <div className="p-lg-4 p-md-4 p-sm-12 p-lg-offset-2 p-md-offset-2">
                                <Button
                                    type="button"
                                    className="p-button-raised p-mr-2"
                                    label={saveLabel}
                                    onClick={(event) => onSave()}
                                />
                            </div>
                            <div className="p-lg-4 p-md-4 p-sm-12">
                                <Button
                                    type="button"
                                    className="p-button-raised p-mr-2"
                                    label={clearLabel}
                                    onClick={(event) => onClear()}
                                />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};
