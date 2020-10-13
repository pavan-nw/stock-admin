import React, { useState} from 'react';
import { InputText } from 'primereact/inputtext';
import { useDispatch} from 'react-redux';
import {DailyStockHeader} from './DailyStockHeader';
import {    
    outgoingStockCountLabel,
    outgoingStockCountPlaceHolder,
} from '../../helpers/constants';
import { DailyStocksFooter } from './DailyStocksFooter';
import { setCloseStock} from '../../features/dailyStocks/actions';

export const DailyStockAddSales: React.FC = () => {
    const dispatch = useDispatch();

    const initialCloseStock = 0;
    const [closeStock, updateCloseStock] = useState(initialCloseStock);
   

    const onCloseStockChange = (outgoingStock:any ) => {        
        updateCloseStock(outgoingStock);
        dispatch(setCloseStock(outgoingStock));        
    }

    return (
        <div>
          <DailyStockHeader> </DailyStockHeader> 
          <DailyStocksFooter><div className="p-field p-grid"><label htmlFor="closeStock" className="p-col-12 p-md-3">
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
                    </div></div></DailyStocksFooter>
        </div>
    );
};
