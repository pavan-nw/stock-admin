import React, { useEffect, useState } from 'react';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { useDispatch, useSelector } from 'react-redux';
import { getProducts } from '../../features/product/selectors';
import {
    getProducts as fetchProducts,
    removeProduct,
} from '../../features/product/productThunk';
import { Product } from '../../features/product/types';
import { InputText } from 'primereact/inputtext';
import './productList.css';
import { EditProductDialog } from './EditProductDialog';
import {
    selectProduct,
    toggleShowEditDialog,
} from '../../features/product/actions';

export const ProductList: React.FC = () => {
    const dispatch = useDispatch();
    const products: Product[] = useSelector(getProducts);
    const [globalFilter, updateGlobalFilter] = useState(null);

    useEffect(() => {
        dispatch(fetchProducts());
    }, []);

    const paginatorLeft = (
        <Button type="button" icon="pi pi-refresh" className="p-button-text" />
    );
    const paginatorRight = (
        <Button type="button" icon="pi pi-cloud" className="p-button-text" />
    );

    const onDelete = (rowData: Product) => {
        console.log('rowData: ', rowData);
        if (rowData.id) {
            dispatch(removeProduct(rowData.id));
        }
    };

    const onEdit = (rowData: Product) => {
        console.log('rowData: ', rowData);
        dispatch(selectProduct(rowData));
        dispatch(toggleShowEditDialog());
    };

    const actionBodyTemplate = (rowData: any) => {
        return (
            <>
                <Button
                    icon="pi pi-pencil"
                    className="p-button-rounded p-button-raised p-button-info p-mr-2"
                    onClick={() => onEdit(rowData)}
                />
                <Button
                    icon="pi pi-trash"
                    className="p-button-rounded p-button-raised p-button-danger"
                    onClick={() => onDelete(rowData)}
                />
            </>
        );
    };

    const indexBodyTemplate = (rowData: any) => {
        return <>{rowData.id}</>;
    };

    const header = (
        <div className="table-header">
            <h3 className="p-m-0">Manage Products</h3>
            <span className="p-input-icon-left">
                <i className="pi pi-search" />
                <InputText
                    type="search"
                    onInput={(e: any) => updateGlobalFilter(e.target.value)}
                    placeholder="Search..."
                />
            </span>
        </div>
    );

    return (
        <div>
            <EditProductDialog />
            <DataTable
                emptyMessage={'No Products Found'}
                value={products}
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
                <Column header="Product ID" body={indexBodyTemplate} />
                <Column field="code" header="Code" sortable />
                <Column field="name" header="Name" sortable />
                <Column field="packaging" header="Packaging" sortable />
                <Column header="Actions" body={actionBodyTemplate} />
            </DataTable>
        </div>
    );
};
