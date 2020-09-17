import React, { useEffect, useRef } from 'react';
import { Toast, ToastMessage } from 'primereact/toast';
import { getShowToast } from '../../features/common/selectors';
import { useSelector } from 'react-redux';

export const ToastWrapper: React.FC = () => {
    const toastData = useSelector(getShowToast);
    const toast = useRef<Toast>(null);

    useEffect(() => {
        if (toast && toastData.show && toast.current) {
            const toastMessage: ToastMessage = {
                severity: 'success',
                detail: toastData.detail,
                summary: toastData.summary,
            };
            toast.current.show(toastMessage);
        }
    });
    return <Toast ref={toast} />;
};
