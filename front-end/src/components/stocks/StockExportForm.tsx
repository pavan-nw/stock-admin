import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { Calendar } from 'primereact/calendar';
import {
    errorOccurred,
    exportLabel,
    fromDateLabel,
    invalidDateRange,
    toDateLabel,
} from '../../helpers/constants';
import { Button } from 'primereact/button';
import { exportStock } from '../../features/dailyStocks/dailyStockThunk';
import { formatDate } from '../../helpers/utils';
import { showToast } from '../../features/common/actions';

export const StockExportForm: React.FC = () => {
    const dispatch = useDispatch();
    const initialFromDate = new Date();
    const initialToDate = new Date();
    const [fromDate, setFromDate] = useState(initialFromDate); //useSelector(getFromDate);
    const [toDate, setToDate] = useState(initialToDate); //useSelector(getToDate);
    const shopCode = 'shop01'; //useSelector(getShopCode);

    const onExport = () => {
        console.log('on export');
        const fromDateString = formatDate(fromDate).replaceAll('/', '-');
        const toDateString = formatDate(toDate).replaceAll('/', '-');
        if (fromDate.getTime() <= toDate.getTime()) {
            dispatch(exportStock(shopCode, fromDateString, toDateString));
        } else {
            dispatch(showToast(errorOccurred, invalidDateRange, 'error'));
        }
    };

    return (
        <div>
            <div className="p-fluid">
                <div className="p-field p-grid">
                    <label htmlFor="fromDate" className="p-col-12 p-md-3">
                        {fromDateLabel}
                    </label>
                    <div className="p-field p-col-12 p-md-4">
                        <Calendar
                            id="icon"
                            viewDate={fromDate}
                            dateFormat="dd/mm/yy"
                            value={fromDate}
                            onViewDateChange={(event: any) =>
                                setFromDate(event.value)
                            }
                            onChange={(event: any) =>
                                setFromDate(event.target.value)
                            }
                            showIcon
                        />
                    </div>
                    <label htmlFor="toDate" className="p-col-12 p-md-3">
                        {toDateLabel}
                    </label>
                    <div className="p-field p-col-12 p-md-4">
                        <Calendar
                            id="icon"
                            viewDate={toDate}
                            dateFormat="dd/mm/yy"
                            value={toDate}
                            onViewDateChange={(event: any) =>
                                setToDate(event.value)
                            }
                            onChange={(event: any) =>
                                setToDate(event.target.value)
                            }
                            showIcon
                        />
                    </div>
                    <div className="p-lg-4 p-md-4 p-sm-12 p-lg-offset-2 p-md-offset-2">
                        <Button
                            type="button"
                            className="p-button-raised p-mr-2"
                            label={exportLabel}
                            onClick={(event) => onExport()}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
};
