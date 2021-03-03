import {applyMiddleware, createStore} from 'redux'
import thunk from 'redux-thunk'
import {createLogger} from "redux-logger";
import rootReducer from "../reducers";
import {composeWithDevTools} from "redux-devtools-extension";

const loggerMiddleware = createLogger(
    {
        predicate : ()=> process.env.NODE_ENV === 'development'
    }
);

const store = createStore(
    rootReducer,
    {},
    composeWithDevTools(
        applyMiddleware(thunk, loggerMiddleware)
    )
)

export default store;