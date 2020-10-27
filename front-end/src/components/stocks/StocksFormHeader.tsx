import React, { useEffect } from 'react';
import { Dropdown } from 'primereact/dropdown';
import { useDispatch, useSelector } from 'react-redux';
import { LocalPackaging, Product } from '../../features/stocks/types';
import {
    getProducts,
    getCurrentStockProduct,
    getCurrentStockProductPackaging,
    getCurrentStockDate,
} from '../../features/stocks/selectors';
import { Calendar } from 'primereact/calendar';
import {
    productNameLabel,
    productNamePlaceHolder,
    productPackagingLabel,
    productPackagingPlaceHolder,
    stockDateLabel,
    packaging,
} from '../../helpers/constants';
import { getProducts as fetchProducts } from '../../features/products/productThunk';
import {
    setProduct,
    setProductPackaging,
    setStockDate,
} from '../../features/stocks/actions';
import { getShopCode } from '../../features/login/selectors';
import { getUniqueProducts } from '../../helpers/utils';

export const StocksFormHeader: React.FC = () => {
    const dispatch = useDispatch();
    const products: Product[] | [] = useSelector(getProducts);
    const uniqueProducts = getUniqueProducts(products);    
    const shopCode = useSelector(getShopCode);
    const selectedProduct = useSelector(getCurrentStockProduct);
    const selectedPackaging = useSelector(getCurrentStockProductPackaging);
    const selectedStockDate = useSelector(getCurrentStockDate);

    useEffect(() => {
        if (products.length == 0) {
            dispatch(fetchProducts(shopCode));
        }
    }, [shopCode]);

    const onProductNameChange = (product: Product) => {
        dispatch(setProduct(product));
    };

    const onProductPackagingChange = (stockPackaging: LocalPackaging) => {
        dispatch(setProductPackaging(stockPackaging));
    };

    const onDateChange = (date: Date) => {
        dispatch(setStockDate(date));
    };

    return (
        <div>
            <div className="p-fluid">
                <div className="p-field p-grid">
                    <label htmlFor="stockDate" className="p-col-12 p-md-3">
                        {stockDateLabel}
                    </label>
                    <div className="p-field p-col-12 p-md-4">
                        <Calendar
                            id="icon"
                            viewDate={selectedStockDate}
                            dateFormat="dd/mm/yy"
                            value={selectedStockDate}
                            onViewDateChange={(event: any) =>
                                onDateChange(event.value)
                            }
                            onChange={(event: any) =>
                                onDateChange(event.target.value)
                            }
                            showIcon
                        />
                    </div>
                </div>
                <div className="p-field p-grid">
                    <label htmlFor="productName" className="p-col-12 p-md-3">
                        {productNameLabel}
                    </label>
                    <div className="p-col-12 p-md-9">
                        <Dropdown
                            id="productName"
                            value={selectedProduct}
                            options={uniqueProducts}
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
                            options={packaging}
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
