import {combineReducers} from "redux";
import organizationTypesReducers from "./OrganizationTypesReducers";
import organizationsReducers from "./OrganizationsReducers";

const rootReducer = combineReducers(
    {
        organizationTypesReducers
        , organizationsReducers
    }
)

export default rootReducer;
