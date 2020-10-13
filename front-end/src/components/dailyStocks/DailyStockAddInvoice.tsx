import React, { useState} from 'react';
import { InputText } from 'primereact/inputtext';
import { useDispatch} from 'react-redux';
import {DailyStockHeader} from './DailyStockHeader';
import {       
    incomingStockCountLabel,
    incomingStockCountPlaceHolder,
} from '../../helpers/constants';
import { DailyStocksFooter } from './DailyStocksFooter';
import { setOpenStock } from '../../features/dailyStocks/actions';

export const DailyStockAddInvoice: React.FC = () => {
    const dispatch = useDispatch();

    const initialOpenStock = 0;
    const [openStock, updateOpenStock] = useState(initialOpenStock);
   

    const onOpenStockChange = (incomingStock:any ) => {        
        updateOpenStock(incomingStock);
        dispatch(setOpenStock(incomingStock));        
    }

    return (
        <div>
          <DailyStockHeader> </DailyStockHeader> 
          <DailyStocksFooter><div className="p-field p-grid"><label htmlFor="openStock" className="p-col-12 p-md-3">
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
                    </div></div></DailyStocksFooter>
        </div>
    );
};
