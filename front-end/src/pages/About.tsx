import React from 'react';
import { useHistory } from 'react-router-dom';
import { InputText } from 'primereact/inputtext';
import { Card } from 'primereact/card';

export const About: React.FC = () => {
    const history = useHistory();

    return (
        <>
            <div className="p-col-12">
                <div className="p-col-6 p-offset-3">
                    <Card title="Add Products">
                        <div className="p-fluid">
                            <div className="p-field p-grid">
                                <label
                                    htmlFor="firstname4"
                                    className="p-col-12 p-md-2"
                                >
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
                                <label
                                    htmlFor="lastname4"
                                    className="p-col-12 p-md-2"
                                >
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
                        </div>
                    </Card>
                </div>
            </div>
        </>
    );
};
