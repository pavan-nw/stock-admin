import { SystemState } from './types';

export const getCountValue = (state: SystemState) => state.count.value;
export const getShow = (state: SystemState) => state.count.show;

export const selectors = {
    getCountValue,
    getShow,
};
