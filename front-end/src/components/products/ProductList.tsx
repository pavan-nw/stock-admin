import React, { useEffect, useState } from 'react';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { useDispatch, useSelector } from 'react-redux';
import { getProducts } from '../../features/product/selectors';
import { getProducts as fetchProducts } from '../../features/product/productThunk';
import { Product } from '../../features/product/types';
import { Toolbar } from 'primereact/toolbar';
import { InputText } from 'primereact/inputtext';
import './productList.css';

const ProductList: React.FC = () => {
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

    const actionBodyTemplate = (rowData: any) => {
        return (
            <>
                <Button
                    icon="pi pi-pencil"
                    className="p-button-rounded p-button-raised p-button-info p-mr-2"
                    // onClick={() => this.editProduct(rowData)}
                />
                <Button
                    icon="pi pi-trash"
                    className="p-button-rounded p-button-raised p-button-danger"
                    // onClick={() => this.confirmDeleteProduct(rowData)}
                />
            </>
        );
    };

    const leftToolbarTemplate = () => {
        return (
            <>
                <Button
                    label="New"
                    icon="pi pi-plus"
                    className="p-button-success p-mr-2"
                    // onClick={this.openNew}
                />
                <Button
                    label="Delete"
                    icon="pi pi-trash"
                    className="p-button-danger"
                    // onClick={this.confirmDeleteSelected}
                    // disabled={
                    //     !this.state.selectedProducts ||
                    //     !this.state.selectedProducts.length
                    // }
                />
            </>
        );
    };

    const rightToolbarTemplate = () => {
        return (
            <>
                <Button
                    label="Export"
                    icon="pi pi-upload"
                    className="p-button-help"
                    // onClick={this.exportCSV}
                />
            </>
        );
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
            <Toolbar
                className="p-mb-4"
                left={leftToolbarTemplate}
                right={rightToolbarTemplate}
            />
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
                    selectionMode="multiple"
                    headerStyle={{ width: '3rem' }}
                />
                <Column field="code" header="Code" sortable />
                <Column field="name" header="Name" sortable />
                <Column field="packaging" header="Packaging" sortable />
                <Column body={actionBodyTemplate} />
            </DataTable>
        </div>
    );
};

export default ProductList;
