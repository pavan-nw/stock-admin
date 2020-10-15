import React, { useEffect, useState } from 'react';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { useDispatch, useSelector } from 'react-redux';
import { getStocks } from '../../features/dailyStocks/selectors';
import {
    getStocks as fetchStocks,    
} from '../../features/dailyStocks/dailyStockThunk';
import { Stocks } from '../../features/dailyStocks/types';
import { InputText } from 'primereact/inputtext';
import {    
    dailyStockDateLabel,
    incomingStockCountLabel,
    ManageStocksLabel,
    outgoingStockCountLabel,
    productCodeLabel,    
    productNameLabel,
    productPackagingLabel,
    searchLabel,    
    totalStockCountLabel
} from '../../helpers/constants';
import {formatDate} from '../../helpers/utils'

export const DailyStockList: React.FC = () => {
    const dispatch = useDispatch();
    const stocks: Stocks[] = useSelector(getStocks);
    const [globalFilter, updateGlobalFilter] = useState(null);

    useEffect(() => {
        dispatch(fetchStocks());
    }, []);

    const paginatorLeft = (
        <Button type="button" icon="pi pi-refresh" className="p-button-text" />
    );
    const paginatorRight = (
        <Button type="button" icon="pi pi-cloud" className="p-button-text" />
    );   

    const indexBodyTemplate = (rowData: any) => {
        return <>{rowData.product.code}</>;
    };

    const dateBodyTemplate = (rowData: any) => {                 
        return <>{formatDate(rowData.stockDate)}</>;
    };

    const header = (
        <div className="table-header">
            <h3 className="p-m-0">{ManageStocksLabel}</h3>
            <span className="p-input-icon-left">
                <i className="pi pi-search" />
                <InputText
                    type="search"
                    onInput={(e: any) => updateGlobalFilter(e.target.value)}
                    placeholder={searchLabel}
                />
            </span>
        </div>
    );

    return (
        <div>            
            <DataTable
                emptyMessage={'No Stocks Found'}
                value={stocks}
                className="p-datatable-striped"
                paginator
                rowHover
                header={header}
                globalFilter={globalFilter}
                paginatorTemplate="CurrentPageReport FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink RowsPerPageDropdown"
                currentPageReportTemplate="Showing {first} to {last} of {totalRecords}"
                rows={10}
                rowsPerPageOptions={[5, 10, 15]}
                paginatorLeft={paginatorLeft}
                paginatorRight={paginatorRight}
            >
                <Column
                    // selectionMode="single"
                    headerStyle={{ width: '3rem' }}
                />
                <Column header={productCodeLabel} body={indexBodyTemplate} />
                <Column body={dateBodyTemplate} header={dailyStockDateLabel} sortable />
                <Column field="product.name" header={productNameLabel} sortable />
                <Column field="product.packaging" header={productPackagingLabel} sortable />
                <Column field="openingStock" header={incomingStockCountLabel} sortable />
                <Column field="closingStock" header={outgoingStockCountLabel} sortable />
                <Column field="totalStock" header={totalStockCountLabel} sortable />                
            </DataTable>
        </div>
    );
};