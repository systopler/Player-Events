import createHistory from 'history/createBrowserHistory';
import React, {Component, Suspense} from "react";
import MCircularProgress from "../components/elements/MCircularProgress";
import {Route, Switch} from "react-router";
import NotFoundPage from "../application/pages/NotFoundPage";

let history = createHistory();

class Routes extends Component {
    render(){
        return (
            <Suspense fallback={<div style={{textAlign: 'center'}}><MCircularProgress thickness={3} /></div>}>
                <Switch history={history}>
                    <Route component={NotFoundPage}/>
                </Switch>
            </Suspense>
        );
    }
}

export default Routes;