import React from 'react';
import { Dialog } from 'primereact/dialog';
import { ProductForm } from './ProductForm';
import {
    selectProduct,
    toggleShowEditDialog,
} from '../../features/product/actions';
import { useDispatch, useSelector } from 'react-redux';
import { getShowEditProductDialog } from '../../features/product/selectors';

export const EditProductDialog: React.FC = () => {
    const dispatch = useDispatch();
    const showDialog = useSelector(getShowEditProductDialog);
    return (
        <Dialog
            header="Edit Product"
            style={{ width: '35vw' }}
            onHide={() => {
                dispatch(selectProduct(null));
                if (showDialog) {
                    dispatch(toggleShowEditDialog());
                }
            }}
            visible={showDialog}
        >
            <ProductForm />
        </Dialog>
    );
};
