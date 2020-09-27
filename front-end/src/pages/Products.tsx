import React from 'react';
import { TabPanel, TabView } from 'primereact/tabview';
import { ProductForm } from '../components/products/ProductForm';
import { ProductList } from '../components/products/ProductList';

export const Products: React.FC = () => {
    return (
        <div className="p-lg-12 p-md-12 p-sm-12">
            <div className="p-lg-10 p-md-12 p-sm-12 p-lg-offset-1">
                <TabView>
                    <TabPanel header="View Products">
                        <ProductList />
                    </TabPanel>
                    <TabPanel header="Add Product">
                        <ProductForm />
                    </TabPanel>
                </TabView>
            </div>
        </div>
    );
};
