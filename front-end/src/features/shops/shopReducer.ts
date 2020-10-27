import { GET_SHOPS, ShopActionTypes, ShopData } from './types';

const initialState: ShopData = {
    shops: [],
};

export default (state = initialState, action: ShopActionTypes) => {
    switch (action.type) {
        case GET_SHOPS:
            return {
                ...state,
                shops: action.shops,
            };
        default:
            return state;
    }
};
