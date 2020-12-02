import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { TabPanel, TabView } from 'primereact/tabview';
import { StockList } from '../components/stocks/StockList';
import { AddInvoice } from '../components/stocks/AddInvoice';
import { AddSales } from '../components/stocks/AddSales';
import {
    viewStocksTitle,
    addInvoiceTitle,
    addSalesTitle,
    exportLabel,
} from '../helpers/constants';
import { clearCurrentStock } from '../features/stocks/actions';
import { StockExportForm } from '../components/stocks/StockExportForm';

export const Stocks: React.FC = () => {
    const initIndex = 0;
    const [activeIndex, updateActiveIndex] = useState(initIndex);
    const dispatch = useDispatch();

    const updateTab = (index: number) => {
        updateActiveIndex(index);
        dispatch(clearCurrentStock());
    };

    return (
        <div className="p-lg-12 p-md-12 p-sm-12">
            <div className="p-lg-10 p-md-12 p-sm-12 p-lg-offset-1">
                <TabView
                    activeIndex={activeIndex}
                    renderActiveOnly={true}
                    onTabChange={(e) => {
                        updateTab(e.index);
                    }}
                >
                    <TabPanel header={viewStocksTitle}>
                        <StockList />
                    </TabPanel>
                    <TabPanel header={addInvoiceTitle}>
                        <AddInvoice />
                    </TabPanel>
                    <TabPanel header={addSalesTitle}>
                        <AddSales />
                    </TabPanel>
                    <TabPanel header={exportLabel}>
                        <StockExportForm/>
                    </TabPanel>
                </TabView>
            </div>
        </div>
    );
};
