export const getErrorMessageToShow: any = (e: any) => {
    if (e.response) {
        console.error('e: ', e.response);
        return e.response.data.payload
            ? e.response.data.payload.errorMessage
            : e.response.data.error.message;
    } else {
        console.error('e: ', e.toString());
        return e.response.data.payload.errorMessage;
    }
};

export const checkSuccess = ({ status }: any) => status === 'Success';
