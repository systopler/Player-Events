import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './application/App';
import * as serviceWorker from './serviceWorker';
import Axios from 'axios';
import * as qs from "qs";
import {HashRouter} from "react-router-dom";

Axios.defaults.paramsSerializer = (params) => {
    return qs.stringify(params, { arrayFormat: 'repeat' })
}

console.log("process.env.NODE_ENV", process.env.NODE_ENV);

if (process.env.NODE_ENV === 'development') {
    Axios.defaults.baseURL = 'http://localhost:8080';
}

Axios.interceptors.response.use(
    (response) => {
        return response
    }
);

ReactDOM.render(
  <React.StrictMode>
      <HashRouter>
          <App />
      </HashRouter>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
