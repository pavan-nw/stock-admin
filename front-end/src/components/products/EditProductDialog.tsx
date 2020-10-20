import React from 'react';
import { Dialog } from 'primereact/dialog';
import { ProductForm } from './ProductForm';
import {
    selectProduct,
    toggleShowEditDialog,
} from '../../features/products/actions';
import { useDispatch, useSelector } from 'react-redux';
import { getShowEditProductDialog } from '../../features/products/selectors';
import { editProductTitle } from '../../helpers/constants';

export const EditProductDialog: React.FC = () => {
    const dispatch = useDispatch();
    const showDialog = useSelector(getShowEditProductDialog);
    return (
        <Dialog
            header={editProductTitle}
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
