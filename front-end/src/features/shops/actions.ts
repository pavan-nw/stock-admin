import { GET_SHOPS, GetShopsAction, Shop } from './types';

export const fetchShops = (shops: Shop[]): GetShopsAction => {
    return {
        type: GET_SHOPS,
        shops,
    };
};
