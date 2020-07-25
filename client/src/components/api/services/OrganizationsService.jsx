import axios from "axios";

export function findOrganizationById(id){
    return axios.get(
        '/organizations/' + id
    );
}
export function findOrganizationAll(){
    return axios.get(
        '/organizations'
    );
}

export function findOrganizationTypesAll(){
    return axios.get(
        '/organizations/types'
    );
}