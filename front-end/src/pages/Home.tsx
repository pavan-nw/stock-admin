import React from 'react';
import { Card } from 'primereact/card';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';

export const Home: React.FC = () => {
    const products = [
        {
            code: 1,
            name: 'Product 1',
            category: 'Category 1',
            quantity: '100',
        },
        {
            code: 2,
            name: 'Product 2',
            category: 'Category 2',
            quantity: '101',
        },
        {
            code: 3,
            name: 'Product 3',
            category: 'Category 1',
            quantity: '100',
        },
        {
            code: 4,
            name: 'Product 4',
            category: 'Category 2',
            quantity: '101',
        },
        {
            code: 5,
            name: 'Product 5',
            category: 'Category 1',
            quantity: '100',
        },
        {
            code: 6,
            name: 'Product 6',
            category: 'Category 2',
            quantity: '101',
        },
        {
            code: 7,
            name: 'Product 7',
            category: 'Category 1',
            quantity: '100',
        },
        {
            code: 8,
            name: 'Product 8',
            category: 'Category 2',
            quantity: '101',
        },
        {
            code: 9,
            name: 'Product 9',
            category: 'Category 1',
            quantity: '100',
        },
        {
            code: 10,
            name: 'Product 10',
            category: 'Category 2',
            quantity: '101',
        },
        {
            code: 11,
            name: 'Product 11',
            category: 'Category 1',
            quantity: '100',
        },
        {
            code: 12,
            name: 'Product 12',
            category: 'Category 2',
            quantity: '101',
        },
        {
            code: 13,
            name: 'Product 13',
            category: 'Category 1',
            quantity: '100',
        },
        {
            code: 14,
            name: 'Product 14',
            category: 'Category 2',
            quantity: '101',
        },
        {
            code: 15,
            name: 'Product 15',
            category: 'Category 1',
            quantity: '100',
        },
        {
            code: 16,
            name: 'Product 16',
            category: 'Category 2',
            quantity: '101',
        },
        {
            code: 17,
            name: 'Product 17',
            category: 'Category 1',
            quantity: '100',
        },
        {
            code: 18,
            name: 'Product 18',
            category: 'Category 2',
            quantity: '101',
        },
        {
            code: 19,
            name: 'Product 19',
            category: 'Category 1',
            quantity: '100',
        },
        {
            code: 20,
            name: 'Product 20',
            category: 'Category 2',
            quantity: '101',
        },
        {
            code: 21,
            name: 'Product 21',
            category: 'Category 1',
            quantity: '100',
        },
        {
            code: 22,
            name: 'Product 22',
            category: 'Category 2',
            quantity: '101',
        },
        {
            code: 23,
            name: 'Product 23',
            category: 'Category 1',
            quantity: '100',
        },
        {
            code: 23,
            name: 'Product 24',
            category: 'Category 2',
            quantity: '101',
        },
        {
            code: 25,
            name: 'Product 25',
            category: 'Category 1',
            quantity: '100',
        },
    ];

    const paginatorLeft = (
        <Button type="button" icon="pi pi-refresh" className="p-button-text" />
    );
    const paginatorRight = (
        <Button type="button" icon="pi pi-cloud" className="p-button-text" />
    );

    return (
        <div className="p-lg-12 p-md-12 p-sm-12">
            <div className="p-lg-10 p-md-12 p-sm-12 p-lg-offset-1">
                <Card title="Stock Admin Home">
                    <p>
                        This is cool home page of stock admin application, built
                        with react, redux and typescript. Prime react is the
                        component library used to style the application and
                        responsible for look and feel
                    </p>
                </Card>
            </div>
            <div className="p-lg-10 p-md-12 p-sm-12 p-lg-offset-1">
                <Card title="Sample Datatable">
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
                        <Column field="code" header="Code" sortable />
                        <Column field="name" header="Name" sortable filter />
                        <Column
                            field="category"
                            header="Category"
                            sortable
                            filter
                        />
                        <Column field="quantity" header="Quantity" sortable />
                    </DataTable>
                </Card>
            </div>
        </div>
    );
};
