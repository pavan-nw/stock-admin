import React from 'react';
import { TabPanel, TabView } from 'primereact/tabview';
import { ProductForm } from '../components/products/ProductForm';
import { DailyStockList } from '../components/dailyStocks/DailyStockList';
import { DailyStockForm } from '../components/dailyStocks/DailyStockForm';
import { addProductTitle, viewProductsTitle,viewDailyStocksTitle,addDailyStockTitle } from '../helpers/constants';

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
                </TabView>
            </div>
        </div>
    );
};
