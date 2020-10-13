import React, { useState,useEffect } from 'react';
import { Dropdown } from 'primereact/dropdown';
import { useDispatch, useSelector } from 'react-redux';
import { Product } from '../../features/dailyStocks/types';
import { getSelectedProduct,getProducts } from '../../features/dailyStocks/selectors';
import { Calendar } from 'primereact/calendar';
import {   
    productNameLabel,
    productNamePlaceHolder,
    productPackagingLabel,
    productPackagingPlaceHolder,    
    dailyStockDateLabel    
} from '../../helpers/constants';
import {
    getProducts as fetchProducts    
} from '../../features/product/productThunk';
import { setProductName, setProductPackaging, setStockDate } from '../../features/dailyStocks/actions';

export const DailyStockHeader: React.FC = () => {
    const dispatch = useDispatch();
    const selectedProduct: Product | null = useSelector(getSelectedProduct);
    const products: Product[] | [] = useSelector(getProducts);

    const initialProductName = selectedProduct ? selectedProduct : {name:""};
    const initialPackaging = selectedProduct ? selectedProduct : {name:""};
    const initialStockDate = new Date();   

    const [selectedPackaging, updatePackaging] = useState(initialPackaging);
    const [stockDate, updateStockDate] = useState(initialStockDate);
    const [productName, updateProductName] = useState(initialProductName);

    useEffect(() => {        
        if(products.length==0){                
            dispatch(fetchProducts());
        } 
    }, [])

    const packagings = [
        { name: '1 Ml' },
        { name: '10 Ml' },
        { name: '1 Liter' },
        { name: '10 Gram' },
        { name: '200 Gram' },
        { name: '1 Kg' },
        { name: '2 Kg' },
        { name: '3 Kg' },
        { name: '5 Kg' },
        { name: '10 Kg' },
        { name: '15 Kg' },
    ];    

    const onProductNameChange = (product:Product ) => {        
        updateProductName(product);
        dispatch(setProductName(product.name));        
    }

    const onProductPackagingChange = (packaging:any) => {
        updatePackaging(packaging);
        dispatch(setProductPackaging(packaging.name));
        console.log(`${selectedPackaging}`);
    }

    const onDateChange = (date:Date ) => {
        updateStockDate(date);
        dispatch(setStockDate(date.toDateString()));
    }

    return (
        <div>
            <div className="p-fluid">
                <div className="p-field p-grid">
                    <label htmlFor="stockDate" className="p-col-12 p-md-3">
                        {dailyStockDateLabel}
                    </label>
                    <div className="p-field p-col-12 p-md-4">
                        <Calendar id="icon" value={stockDate} onChange={(event: any) => onDateChange(event.target.value)} showIcon />
                    </div>
                </div>
                <div className="p-field p-grid">
                    <label htmlFor="productName" className="p-col-12 p-md-3">
                        {productNameLabel}
                    </label>
                    <div className="p-col-12 p-md-9">
                        <Dropdown
                            id="productName"
                            value={productName}
                            options={products}
                            showClear
                            filter
                            optionLabel="name"
                            filterBy="name"
                            onChange={(e) => onProductNameChange(e.value)}
                            placeholder={productNamePlaceHolder}
                        />
                    </div>
                </div>
                <div className="p-field p-grid">
                    <label htmlFor="packaging" className="p-col-12 p-md-3">
                        {productPackagingLabel}
                    </label>
                    <div className="p-col-12 p-md-9">
                        <Dropdown
                            id="packaging"
                            value={selectedPackaging}
                            options={packagings}
                            showClear
                            filter
                            optionLabel="name"
                            filterBy="name"
                            onChange={(e) => onProductPackagingChange(e.value)}
                            placeholder={productPackagingPlaceHolder}
                        />
                    </div>
                </div>                               
            </div>
        </div>
    );
};
