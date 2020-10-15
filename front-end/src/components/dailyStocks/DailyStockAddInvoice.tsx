import React from 'react';
import { InputText } from 'primereact/inputtext';
import { useDispatch,useSelector} from 'react-redux';
import {DailyStockHeader} from './DailyStockHeader';
import {       
    incomingStockCountLabel,
    incomingStockCountPlaceHolder,
} from '../../helpers/constants';
import { DailyStocksFooter } from './DailyStocksFooter';
import { setOpenStock } from '../../features/dailyStocks/actions';
import { getOpeningStock } from '../../features/dailyStocks/selectors';

export const DailyStockAddInvoice: React.FC = () => {
    const dispatch = useDispatch();
    const openStock = useSelector(getOpeningStock);    
    
    const onOpenStockChange = (incomingStock:any ) => {        
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
