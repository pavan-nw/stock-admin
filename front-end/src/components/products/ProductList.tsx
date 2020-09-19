import React from 'react';
import { Card } from 'primereact/card';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';

const ProductList: React.FC = () => {
    const products = [
        {
            code: 1,
            name: 'Product 1',
            packaging: '1 Kg',
        },
        {
            code: 2,
            name: 'Product 2',
            packaging: '3 Kg',
            quantity: '101',
        },
        {
            code: 3,
            name: 'Product 3',
            packaging: '1 Kg',
        },
        {
            code: 4,
            name: 'Product 4',
            packaging: '3 Kg',
            quantity: '101',
        },
        {
            code: 5,
            name: 'Product 5',
            packaging: '1 Kg',
        },
        {
            code: 6,
            name: 'Product 6',
            packaging: '3 Kg',
            quantity: '101',
        },
        {
            code: 7,
            name: 'Product 7',
            packaging: '1 Kg',
        },
        {
            code: 8,
            name: 'Product 8',
            packaging: '3 Kg',
            quantity: '101',
        },
        {
            code: 9,
            name: 'Product 9',
            packaging: '1 Kg',
        },
        {
            code: 10,
            name: 'Product 10',
            packaging: '3 Kg',
            quantity: '101',
        },
        {
            code: 11,
            name: 'Product 11',
            packaging: '1 Kg',
        },
        {
            code: 12,
            name: 'Product 12',
            packaging: '3 Kg',
            quantity: '101',
        },
        {
            code: 13,
            name: 'Product 13',
            packaging: '1 Kg',
        },
        {
            code: 14,
            name: 'Product 14',
            packaging: '3 Kg',
            quantity: '101',
        },
        {
            code: 15,
            name: 'Product 15',
            packaging: '1 Kg',
        },
        {
            code: 16,
            name: 'Product 16',
            packaging: '3 Kg',
            quantity: '101',
        },
        {
            code: 17,
            name: 'Product 17',
            packaging: '1 Kg',
        },
        {
            code: 18,
            name: 'Product 18',
            packaging: '3 Kg',
            quantity: '101',
        },
        {
            code: 19,
            name: 'Product 19',
            packaging: '1 Kg',
        },
        {
            code: 20,
            name: 'Product 20',
            packaging: '3 Kg',
            quantity: '101',
        },
        {
            code: 21,
            name: 'Product 21',
            packaging: '1 Kg',
        },
        {
            code: 22,
            name: 'Product 22',
            packaging: '3 Kg',
            quantity: '101',
        },
        {
            code: 23,
            name: 'Product 23',
            packaging: '1 Kg',
        },
        {
            code: 23,
            name: 'Product 24',
            packaging: '3 Kg',
            quantity: '101',
        },
        {
            code: 25,
            name: 'Product 25',
            packaging: '1 Kg',
        },
    ];

    const paginatorLeft = (
        <Button type="button" icon="pi pi-refresh" className="p-button-text" />
    );
    const paginatorRight = (
        <Button type="button" icon="pi pi-cloud" className="p-button-text" />
    );

    return (
        <div>
            <Card title="Products">
                <DataTable
                    value={products}
                    className="p-datatable-striped"
                    paginator
                    paginatorTemplate="CurrentPageReport FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink RowsPerPageDropdown"
                    currentPageReportTemplate="Showing {first} to {last} of {totalRecords}"
                    rows={10}
                    rowsPerPageOptions={[5, 10, 15]}
                    paginatorLeft={paginatorLeft}
                    paginatorRight={paginatorRight}
                >
                    {/*<Column*/}
                    {/*    field="slno"*/}
                    {/*    // body={indexValue()}*/}
                    {/*    header="Sl.No"*/}
                    {/*    sortable*/}
                    {/*/>*/}
                    <Column field="code" header="Code" sortable />
                    <Column field="name" header="Name" sortable filter />
                    <Column
                        field="packaging"
                        header="Packaging"
                        sortable
                        filter
                    />
                </DataTable>
            </Card>
        </div>
    );
};

export default ProductList;
