import React from 'react';
import { InputText } from 'primereact/inputtext';
import { useDispatch, useSelector } from 'react-redux';
import { StocksFormHeader } from './StocksFormHeader';
import {
    outgoingStockCountLabel,
    outgoingStockCountPlaceHolder,
} from '../../helpers/constants';
import { StocksFormFooter } from './StocksFormFooter';
import { setCloseStock } from '../../features/stocks/actions';
import { getClosingStock } from '../../features/stocks/selectors';

export const AddSales: React.FC = () => {
    const dispatch = useDispatch();
    const closeStock = useSelector(getClosingStock);

    const onCloseStockChange = (outgoingStock: any) => {
        dispatch(setCloseStock(outgoingStock));
    };

    return (
        <div>
            <StocksFormHeader> </StocksFormHeader>
            <StocksFormFooter>
                <div className="p-field p-grid">
                    <label htmlFor="closeStock" className="p-col-12 p-md-3">
                        {outgoingStockCountLabel}
                    </label>
                    <div className="p-col-12 p-md-9">
                        <InputText
                            id="closeStock"
                            type="number"
                            value={closeStock}
                            onChange={(event: any) =>
                                onCloseStockChange(event.target.value)
                            }
                            placeholder={outgoingStockCountPlaceHolder}
                        />
                    </div>
                </div>
            </StocksFormFooter>
        </div>
    );
};
