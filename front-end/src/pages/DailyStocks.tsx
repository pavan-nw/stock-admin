import React, {useState} from 'react';
import { useDispatch } from 'react-redux';
import { TabPanel, TabView } from 'primereact/tabview';
import { DailyStockList } from '../components/dailyStocks/DailyStockList';
import {DailyStockAddInvoice  } from '../components/dailyStocks/DailyStockAddInvoice';
import {DailyStockAddSales  } from '../components/dailyStocks/DailyStockAddSales';
import {viewDailyStocksTitle,addInvoiceTitle,addSalesTitle } from '../helpers/constants';
import { clearCurrentStock } from '../features/dailyStocks/actions';

export const DailyStocks: React.FC = () => {

    const initIndex = 0;
    const [activeIndex, updateActiveIndex] = useState(initIndex);
    const dispatch = useDispatch()

    const updateTab = (index:number) => {
        updateActiveIndex(index);
        dispatch(clearCurrentStock());
    }; 

    return (
        <div className="p-lg-12 p-md-12 p-sm-12">
            <div className="p-lg-10 p-md-12 p-sm-12 p-lg-offset-1">
                <TabView activeIndex={activeIndex} 
                renderActiveOnly={false}  
                onTabChange={(e) => { updateTab(e.index)}}>
                    <TabPanel header={viewDailyStocksTitle}>
                        <DailyStockList />
                    </TabPanel>                    
                    <TabPanel header={addInvoiceTitle}>
                        <DailyStockAddInvoice />
                    </TabPanel> 
                    <TabPanel header={addSalesTitle}>
                        <DailyStockAddSales />
                    </TabPanel>                    
                </TabView>
            </div>
        </div>
    );
};
