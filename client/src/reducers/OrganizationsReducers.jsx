import {LOADING, ORGANIZATIONS, SET, START, STOP} from "../constants";

let initialState = {
    records  : null,
    loading : false,
    loaded  : false,
}

export default function organizationsReducers(state = initialState, action) {
    switch (action.type) {

        case START + LOADING + ORGANIZATIONS: {
            return {
                ...state,
                loading: true,
            }
        }
        case STOP + LOADING + ORGANIZATIONS: {
            return {
                ...state,
                loading: false,
            }
        }
        case SET + LOADING + ORGANIZATIONS: {
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
