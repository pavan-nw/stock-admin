import React from 'react';
import { TabPanel, TabView } from 'primereact/tabview';
import { DailyStockList } from '../components/dailyStocks/DailyStockList';
import { DailyStockForm } from '../components/dailyStocks/DailyStockForm';
import {DailyStockAddInvoice  } from '../components/dailyStocks/DailyStockAddInvoice';
import {DailyStockAddSales  } from '../components/dailyStocks/DailyStockAddSales';
import {viewDailyStocksTitle,addDailyStockTitle,addInvoiceTitle,addSalesTitle } from '../helpers/constants';

export const DailyStocks: React.FC = () => {
    return (
        <div className="p-lg-12 p-md-12 p-sm-12">
            <div className="p-lg-10 p-md-12 p-sm-12 p-lg-offset-1">
                <TabView renderActiveOnly={false}>
                    <TabPanel header={viewDailyStocksTitle}>
                        <DailyStockList />
                    </TabPanel>
                    <TabPanel header={addDailyStockTitle}>
                        <DailyStockForm />
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
