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
            style={{ width: '15vw' }}
            visible={spinnerDialogData.show}
            onHide={() => {}}
        >
            <div className="p-col-12">
                <div className="p-col-6 p-offset-3">
                    <div className="p-d-flex p-dir-col">
                        <p className="p-ml-auto" style={{ fontWeight: 'bold' }}>
                            {spinnerDialogData.message}
                        </p>
                        <ProgressSpinner
                            className="p-ml-auto"
                            style={{ width: '50px', height: '50px' }}
                            strokeWidth="8"
                            fill="#EEEEEE"
                        />
                    </div>
                </div>
            </div>
        </Dialog>
    );
};
