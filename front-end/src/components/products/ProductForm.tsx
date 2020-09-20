import React, { useState } from 'react';
import { Card } from 'primereact/card';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { MultiSelect } from 'primereact/multiselect';
import { Dropdown } from 'primereact/dropdown';

const ProductForm: React.FC = () => {
    const [selectedPackagings, updatePackaging] = useState([]);
    const packagings = [
        { name: '1 Mili Liter', code: 'NY1' },
        { name: '10 Mili Liter', code: 'NY2' },
        { name: '1 Liter', code: 'NY3' },
        { name: '10 Gram', code: 'NY4' },
        { name: '200 Gram', code: 'NY' },
        { name: '2 Kilo Gram', code: 'RM' },
        { name: '3 Kilo Gram', code: 'LDN' },
        { name: '5 Kilo Gram', code: 'IST' },
        { name: '10 Kilo Gram', code: 'PRS' },
    ];

    return (
        <div>
            <div className="p-fluid">
                <div className="p-field p-grid">
                    <label htmlFor="firstname4" className="p-col-12 p-md-2">
                        Product Code
                    </label>
                    <div className="p-col-12 p-md-10">
                        <InputText
                            id="firstname4"
                            type="text"
                            placeholder="Enter product code here."
                        />
                    </div>
                </div>
                <div className="p-field p-grid">
                    <label htmlFor="lastname4" className="p-col-12 p-md-2">
                        Product Name
                    </label>
                    <div className="p-col-12 p-md-10">
                        <InputText
                            id="lastname4"
                            type="text"
                            placeholder="Enter product name here."
                        />
                    </div>
                </div>
                <div className="p-field p-grid">
                    <label htmlFor="lastname4" className="p-col-12 p-md-2">
                        Packaging
                    </label>
                    <div className="p-col-12 p-md-10">
                        <Dropdown
                            value={selectedPackagings}
                            options={packagings}
                            showClear
                            filter
                            filterBy="name"
                            onChange={(e) => updatePackaging(e.value)}
                            optionLabel="name"
                            placeholder="Select a Packaging"
                        />
                    </div>
                </div>
                <div className="p-grid p-mt-lg-4 p-mt-md-2 p-mt-sm-1">
                    <div className="p-lg-4 p-md-12 p-sm-12 p-lg-offset-4">
                        <div className="p-grid">
                            <div className="p-lg-6 p-md-6 p-sm-12">
                                <Button
                                    type="button"
                                    className="p-button-raised p-mr-2"
                                    label="Save"
                                />
                            </div>
                            <div className="p-lg-6 p-md-6 p-sm-12">
                                <Button
                                    type="button"
                                    className="p-mr-2 p-button-raised p-button-danger"
                                    label="Cancel"
                                />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ProductForm;
