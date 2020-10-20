import React from 'react';
import { InputText } from 'primereact/inputtext';
import { useDispatch, useSelector } from 'react-redux';
import { StocksFormHeader } from './StocksFormHeader';
import {
    incomingStockCountLabel,
    incomingStockCountPlaceHolder,
} from '../../helpers/constants';
import { StocksFormFooter } from './StocksFormFooter';
import { setOpenStock } from '../../features/stocks/actions';
import { getOpeningStock } from '../../features/stocks/selectors';

export const AddInvoice: React.FC = () => {
    const dispatch = useDispatch();
    const openStock = useSelector(getOpeningStock);

    const onOpenStockChange = (incomingStock: any) => {
        dispatch(setOpenStock(incomingStock));
    };

    return (
        <div>
            <StocksFormHeader> </StocksFormHeader>
            <StocksFormFooter>
                <div className="p-field p-grid">
                    <label htmlFor="openStock" className="p-col-12 p-md-3">
                        {incomingStockCountLabel}
                    </label>
                    <div className="p-col-12 p-md-9">
                        <InputText
                            id="openStock"
                            type="number"
                            value={openStock}
                            onChange={(event: any) =>
                                onOpenStockChange(event.target.value)
                            }
                            placeholder={incomingStockCountPlaceHolder}
                        />
                    </div>
                </div>
            </StocksFormFooter>
        </div>
    );
};
