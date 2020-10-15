export const getErrorMessageToShow: any = (e: any) => {
    if (e.response) {
        console.log('e: ', e.response);
        return e.response.data.payload
            ? e.response.data.payload.errorMessage
            : e.response.data.error.message;
    } else {
        console.error('e: ', e.toString());
        return e.response.data.payload.errorMessage;
    }
};

export const checkSuccess = ({ status }: any) => status === 'Success';

export const formatDate = (date: string | Date) => {
    // Given date =  2020-10-03T00:00:00.000+00:00   
    
    let formattedDate = new Intl.DateTimeFormat('en-IN', {
        year: 'numeric', month: '2-digit', day: '2-digit'
    }); // 10/03/2020   
    
    if(typeof(date)==="string"){
        return formattedDate.format(new Date(date));
    }
    else{
        return formattedDate.format(date);
    }
};
