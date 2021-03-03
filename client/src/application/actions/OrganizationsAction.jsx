import {LOADING, ORGANIZATIONS, SET, START, STOP} from "../../constants";
import {findOrganizationAll} from "../../components/api/services/OrganizationsService";

export function startLoading(){
    return {
        type : START + LOADING + ORGANIZATIONS
    }
}

export function stopLoading(){
    return {
        type : STOP + LOADING + ORGANIZATIONS
    }
}

export function set(value){
    return {
        type : SET + LOADING + ORGANIZATIONS,
        payload : value,
    }
}

export function replace(value){
    return (dispatch, getState) => {
        dispatch(startLoading());
        dispatch(set(value));
        dispatch(stopLoading());
    }
}

export function load(){
    return (dispatch, getState) => {
        dispatch(startLoading());

        findOrganizationAll().then(
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
