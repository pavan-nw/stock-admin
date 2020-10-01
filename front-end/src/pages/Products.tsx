import React from 'react';
import { TabPanel, TabView } from 'primereact/tabview';
import { ProductForm } from '../components/products/ProductForm';
import { ProductList } from '../components/products/ProductList';
import { addProductTitle, viewProductsTitle } from '../helpers/constants';

export const Products: React.FC = () => {
    return (
        <div className="p-lg-12 p-md-12 p-sm-12">
            <div className="p-lg-10 p-md-12 p-sm-12 p-lg-offset-1">
                <TabView>
                    <TabPanel header={viewProductsTitle}>
                        <ProductList />
                    </TabPanel>
                    <TabPanel header={addProductTitle}>
                        <ProductForm />
                    </TabPanel>
                </TabView>
            </div>
        </div>
    );
};
