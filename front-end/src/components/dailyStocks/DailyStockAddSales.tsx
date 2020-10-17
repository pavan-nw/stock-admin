import React from 'react';
import { InputText } from 'primereact/inputtext';
import { useDispatch, useSelector } from 'react-redux';
import { DailyStockHeader } from './DailyStockHeader';
import {
    outgoingStockCountLabel,
    outgoingStockCountPlaceHolder,
} from '../../helpers/constants';
import { DailyStocksFooter } from './DailyStocksFooter';
import { setCloseStock } from '../../features/dailyStocks/actions';
import { getClosingStock } from '../../features/dailyStocks/selectors';

export const DailyStockAddSales: React.FC = () => {
    const dispatch = useDispatch();
    const closeStock = useSelector(getClosingStock);

    const onCloseStockChange = (outgoingStock: any) => {
        dispatch(setCloseStock(outgoingStock));
    };

    return (
        <div>
            <DailyStockHeader> </DailyStockHeader>
            <DailyStocksFooter>
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
            </DailyStocksFooter>
        </div>
    );
};
