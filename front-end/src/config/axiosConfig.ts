import axios from 'axios';
import { baseURL } from '../helpers/constants';

const axiosInstance = axios.create({
    baseURL,
    headers: {
        'content-type': 'application/json',
    },
});

export default axiosInstance;
