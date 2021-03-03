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

export function saveOrganization(organization){
    return axios.post(
        '/organizations/save'
        ,{
            ...organization
        }
    );
}

export function deleteOrganization(organization){
    return axios.delete(
        '/organizations/delete'
        ,{
            data : {
                ...organization
            }
        }
    );
}
