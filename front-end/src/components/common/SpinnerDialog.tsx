import React from 'react';
import { getShowSpinnerDialog } from '../../features/common/selectors';
import { useSelector } from 'react-redux';
import { ProgressSpinner } from 'primereact/progressspinner';
import { Dialog } from 'primereact/dialog';

export const SpinnerDialog: React.FC = () => {
    const spinnerDialogData = useSelector(getShowSpinnerDialog);
    return (
        <Dialog
            closable={false}
            style={{ width: '25vw' }}
            visible={spinnerDialogData.show}
            onHide={() => {}}
        >
            <div className="p-grid">
                <div className="p-col-2">
                    <ProgressSpinner
                        className="p-mr-2"
                        style={{ width: '50px', height: '50px' }}
                        strokeWidth="8"
                        fill="#EEEEEE"
                    />
                </div>
                <div className="p-col-10">
                    <p style={{ fontWeight: 'bold' }}>
                        {spinnerDialogData.message}
                    </p>
                </div>
            </div>
        </Dialog>
    );
};
