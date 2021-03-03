import {LOADING, ORGANIZATION_TYPES, SET, START, STOP} from "../../constants";
import {findOrganizationTypesAll} from "../../components/api/services/OrganizationsService";


export function startLoading(){
    return {
        type : START + LOADING + ORGANIZATION_TYPES
    }
}

export function stopLoading(){
    return {
        type : STOP + LOADING + ORGANIZATION_TYPES
    }
}

export function set(value){
    return {
        type : SET + LOADING + ORGANIZATION_TYPES,
        payload : value,
    }
}

export function load(){
    return (dispatch, getState) => {
        dispatch(startLoading());

        findOrganizationTypesAll().then(
            (response) => {
                dispatch(
                    set(
                        response.data
                    )
                );
                dispatch(stopLoading());
            }
        );
    }
}
