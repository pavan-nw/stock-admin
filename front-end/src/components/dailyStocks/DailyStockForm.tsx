import React, { useState,useEffect } from 'react';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
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
    saveLabel,
    dailyStockDateLabel,
    incomingStockCountLabel,
    outgoingStockCountLabel,
    incomingStockCountPlaceHolder,
    outgoingStockCountPlaceHolder,
    clearLabel
} from '../../helpers/constants';
import {
    getProducts as fetchProducts    
} from '../../features/product/productThunk';

export const DailyStockForm: React.FC = () => {
    const dispatch = useDispatch();
    const selectedProduct: Product | null = useSelector(getSelectedProduct);
    const products: Product[] | [] = useSelector(getProducts);

    const initialProductName = selectedProduct ? selectedProduct : {name:""}
    const initialPackaging = selectedProduct ? selectedProduct : {name:""};
    const initialProductCode = selectedProduct ? selectedProduct.code : '';
    const initialStockDate = new Date('10/09/01');
    const initialOpenStock = 0;
    const initialCloseStock = 0;

    const [selectedPackaging, updatePackaging] = useState(initialPackaging);
    const [stockDate, updateStockDate] = useState(initialStockDate);
    const [productName, updateProductName] = useState(initialProductName);    
    const [openStock, updateOpenStock] = useState(initialOpenStock);
    const [closeStock, updateCloseStock] = useState(initialCloseStock);

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

    const onSave = () => {
        console.log(`inside dailystock form ${JSON.stringify(productName)} ${JSON.stringify(selectedPackaging)}`);
        // if (selectedProduct) {
        //     const product: Product = {
        //         id: selectedProduct.id,
        //         code: productCode,
        //         name: productName,
        //         packaging: selectedPackaging,
        //     };
        //     dispatch(editProduct(product));
        // } else {
        //     const product: Product = {
        //         code: productCode,
        //         name: productName,
        //         packaging: selectedPackaging,
        //     };
        //     dispatch(addProduct(product));
        // }
    };

    const onClear = () => {
        updateStockDate(initialStockDate);        
        updateOpenStock(initialOpenStock);
        updateCloseStock(initialCloseStock);
        updateProductName(initialProductName);
        updatePackaging(initialPackaging);
    };

    return (
        <div>
            <div className="p-fluid">
                <div className="p-field p-grid">
                    <label htmlFor="stockDate" className="p-col-12 p-md-3">
                        {dailyStockDateLabel}
                    </label>
                    <div className="p-field p-col-12 p-md-4">
                        <Calendar id="icon" value={stockDate} onChange={(event: any) => updateStockDate(event.target.value)} showIcon />
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
                            onChange={(e) => updateProductName(e.value)}
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
                            onChange={(e) => updatePackaging(e.value)}
                            placeholder={productPackagingPlaceHolder}
                        />
                    </div>
                </div>
                <div className="p-field p-grid">
                    <label htmlFor="openStock" className="p-col-12 p-md-3">
                        {incomingStockCountLabel}
                    </label>
                    <div className="p-col-12 p-md-9">
                        <InputText
                            id="openStock"
                            type="number"
                            value={openStock}
                            onChange={(event: any) =>
                                updateOpenStock(event.target.value)
                            }
                            placeholder={incomingStockCountPlaceHolder}
                        />
                    </div>
                </div>
                <div className="p-field p-grid">
                    <label htmlFor="closeStock" className="p-col-12 p-md-3">
                        {outgoingStockCountLabel}
                    </label>
                    <div className="p-col-12 p-md-9">
                        <InputText
                            id="closingStock"
                            type="number"
                            value={closeStock}
                            onChange={(event: any) =>
                                updateCloseStock(event.target.value)
                            }
                            placeholder={outgoingStockCountPlaceHolder}
                        />
                    </div>
                </div>
                <div className="p-grid p-mt-lg-4 p-mt-md-2 p-mt-sm-1">
                    <div className="p-lg-4 p-md-12 p-sm-12 p-lg-offset-4">
                        <div className="p-grid">
                            <div className="p-lg-4 p-md-4 p-sm-12 p-lg-offset-2 p-md-offset-2">
                                <Button
                                    type="button"
                                    className="p-button-raised p-mr-2"
                                    label={saveLabel}
                                    onClick={(event) => onSave()}
                                />                               
                            </div>
                            <div className="p-lg-4 p-md-4 p-sm-12">
                                <Button
                                    type="button"
                                    className="p-button-raised p-mr-2"
                                    label={clearLabel}
                                    onClick={(event) => onClear()}
                                />                              
                            </div>
                        </div>
                    </div>
                </div>               
            </div>
        </div>
    );
};
