import React, { useEffect } from 'react';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { useDispatch, useSelector } from 'react-redux';
import { getProducts } from '../../features/product/selectors';
import { getProducts as fetchProducts } from '../../features/product/productThunk';
import { Product } from '../../features/product/types';

const ProductList: React.FC = () => {
    const paginatorLeft = (
        <Button type="button" icon="pi pi-refresh" className="p-button-text" />
    );
    const paginatorRight = (
        <Button type="button" icon="pi pi-cloud" className="p-button-text" />
    );

    const products: Array<Product> = useSelector(getProducts);
    console.log('products: ', products);
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(fetchProducts());
    }, []);

    return (
        <div>
            <DataTable
                emptyMessage={'No Products Found'}
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
                <Column field="packaging" header="Packaging" sortable filter />
            </DataTable>
        </div>
    );
};

export default ProductList;
