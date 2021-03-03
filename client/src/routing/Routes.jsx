import createHistory from 'history/createBrowserHistory';
import React, {Component, lazy, Suspense} from "react";
import MCircularProgress from "../components/elements/MCircularProgress";
import {Route, Switch} from "react-router";

const NotFoundPage = lazy(() => import("../application/pages/NotFoundPage"))
const PeoplePage = lazy(() => import("../application/pages/PeoplePage"))
const PersonPage = lazy(() => import("../application/pages/PersonPage"))
const OrganizationsPage = lazy(() => import("../application/pages/OrganizationsPage"))
const OrganizationPage = lazy(() => import("../application/pages/organization/OrganizationPage"))

let history = createHistory();

class Routes extends Component {
    render(){
        return (
            <Suspense fallback={<div style={{textAlign: 'center'}}><MCircularProgress thickness={3} /></div>}>
                <Switch history={history}>
                    <Route exact path='/' component={PeoplePage}/>
                    <Route path='/person' component={PersonPage}/>
                    <Route path='/organizations' component={OrganizationsPage} />
                    <Route path='/organization/:id' component={OrganizationPage}/>
                    <Route path='/organization' component={OrganizationPage}/>
                    <Route component={NotFoundPage}/>
                </Switch>
            </Suspense>
        );
    }
}

export default Routes;
