export const GET_SHOPS = 'GET_SHOPS';

export interface GetShopsAction {
    type: typeof GET_SHOPS;
    shops: Shop[];
}

export interface Shop {
    id: string;
    name: string;
    shopCode: string;
    location: string;
    createdAt: string;
    updatedAt: string;
}

export type ShopActionTypes = GetShopsAction;

export interface ShopData {
    shops: Shop[];
}

export interface ShopState {
    shopState: ShopData;
}
