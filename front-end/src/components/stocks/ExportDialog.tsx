import React from 'react';
import { Dialog } from 'primereact/dialog';
import {    
    toggleExportShowDialog,
} from '../../features/stocks/actions';
import { useDispatch, useSelector } from 'react-redux';
import { getShowDialog } from '../../features/stocks/selectors';
import { exportLabel } from '../../helpers/constants';
import {StockExportForm} from '../stocks/StockExportForm';

export const ExportDialog: React.FC = () => {
    const dispatch = useDispatch();
    const showDialog = useSelector(getShowDialog);
    return (
        <Dialog           
            header={exportLabel}
            style={{ width: '45vw',height:'90vw'}}
            onHide={() => {                
                if (showDialog) {
                    dispatch(toggleExportShowDialog());
                }
            }}
            visible={showDialog}                       
        >
        <StockExportForm/>
        </Dialog>
    );
};
