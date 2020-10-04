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

export const formatDate = (date: string) => {
    // Given date =  2020-10-03T00:00:00.000+00:00       
    let formattedDate = new Intl.DateTimeFormat('en', {
        year: 'numeric', month: '2-digit', day: '2-digit'
    }); // 10/03/2020               
    return formattedDate.format(new Date(date));
};
