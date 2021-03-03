import {LOADING, SET, START, STOP, ORGANIZATION_TYPES} from "../constants";

let initialState = {
    records  : null,
    loading : false,
    loaded  : false,
}

export default function organizationTypesReducers(state = initialState, action) {
    switch (action.type) {

        case START + LOADING + ORGANIZATION_TYPES: {
            return {
                ...state,
                loading: true,
            }
        }
        case STOP + LOADING + ORGANIZATION_TYPES: {
            return {
                ...state,
                loading: false,
            }
        }
        case SET + LOADING + ORGANIZATION_TYPES: {
            return {
                ...state,
                records : action.payload,
                loaded  : true,
            }
        }
        default:
            return state;
    }
}