import React from 'react';
import { Card } from 'primereact/card';

export const About: React.FC = () => {
    return (
        <div className="p-lg-12 p-md-12 p-sm-12">
            <div className="p-lg-10 p-md-12 p-sm-12 p-lg-offset-1">
                <Card title="About Us">Here come about us page.</Card>
            </div>
        </div>
    );
};
