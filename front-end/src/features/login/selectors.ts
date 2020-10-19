import { SessionState } from './types';

export const getShopCode = (state: SessionState) => state.sessionState.shopCode;
