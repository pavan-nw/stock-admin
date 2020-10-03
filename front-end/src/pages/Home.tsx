import React from 'react';
import { Card } from 'primereact/card';

export const Home: React.FC = () => {
    return (
        <div className="p-lg-12 p-md-12 p-sm-12">
            <div className="p-lg-10 p-md-12 p-sm-12 p-lg-offset-1">
                <Card title="Stock Admin Home">
                    <p>
                        This is cool home page of stock admin application, built
                        with react, redux and typescript. Prime react is the
                        component library used to style the application and
                        responsible for look and feel
                    </p>
                </Card>
            </div>
        </div>
    );
};
