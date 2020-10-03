import React, { useState } from 'react';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { Dropdown } from 'primereact/dropdown';
import { useDispatch, useSelector } from 'react-redux';
import { Product } from '../../features/product/types';
import { addProduct, editProduct } from '../../features/product/productThunk';
import { getSelectedProduct } from '../../features/product/selectors';
import {
    productCodeLabel,
    productCodePlaceHolder,
    productNameLabel,
    productNamePlaceHolder,
    productPackagingLabel,
    productPackagingPlaceHolder,
    saveLabel,
} from '../../helpers/constants';

export const ProductForm: React.FC = () => {
    const dispatch = useDispatch();
    const selectedProduct: Product | null = useSelector(getSelectedProduct);

    const initialProductCode = selectedProduct ? selectedProduct.code : '';
    const initialProductName = selectedProduct ? selectedProduct.name : '';
    const initialPackaging = selectedProduct ? selectedProduct.packaging : '';

    const [selectedPackaging, updatePackaging] = useState(initialPackaging);
    const [productCode, updateProductCode] = useState(initialProductCode);
    const [productName, updateProductName] = useState(initialProductName);

    const packagings = [
        '1 Ml',
        '10 Ml',
        '1 Liter',
        '10 Gram',
        '200 Gram',
        '1 Kg',
        '2 Kg',
        '3 Kg',
        '5 Kg',
        '10 Kg',
        '15 Kg',
    ];

    const onSave = () => {
        if (selectedProduct) {
            const product: Product = {
                id: selectedProduct.id,
                code: productCode,
                name: productName,
                packaging: selectedPackaging,
            };
            dispatch(editProduct(product));
        } else {
            const product: Product = {
                code: productCode,
                name: productName,
                packaging: selectedPackaging,
            };
            dispatch(addProduct(product));
        }
    };

    return (
        <div>
            <div className="p-fluid">
                <div className="p-field p-grid">
                    <label htmlFor="productCode" className="p-col-12 p-md-3">
                        {productCodeLabel}
                    </label>
                    <div className="p-col-12 p-md-9">
                        <InputText
                            id="productCode"
                            type="text"
                            value={productCode}
                            onChange={(event: any) =>
                                updateProductCode(event.target.value)
                            }
                            placeholder={productCodePlaceHolder}
                            disabled={selectedProduct !== null}
                        />
                    </div>
                </div>
                <div className="p-field p-grid">
                    <label htmlFor="productName" className="p-col-12 p-md-3">
                        {productNameLabel}
                    </label>
                    <div className="p-col-12 p-md-9">
                        <InputText
                            id="productName"
                            type="text"
                            value={productName}
                            onChange={(event: any) =>
                                updateProductName(event.target.value)
                            }
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
                            filterBy="name"
                            onChange={(e) => updatePackaging(e.value)}
                            placeholder={productPackagingPlaceHolder}
                        />
                    </div>
                </div>
                <div className="p-grid p-mt-lg-4 p-mt-md-2 p-mt-sm-1">
                    <div className="p-lg-4 p-md-12 p-sm-12 p-lg-offset-4">
                        <div className="p-grid">
                            <div className="p-lg-6 p-md-6 p-sm-12 p-lg-offset-3 p-md-offset-3">
                                <Button
                                    type="button"
                                    className="p-button-raised p-mr-2"
                                    label={saveLabel}
                                    onClick={(event) => onSave()}
                                />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};
