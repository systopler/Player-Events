import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './application/App';
import * as serviceWorker from './serviceWorker';
import Axios from 'axios';
import * as qs from "qs";
import {HashRouter} from "react-router-dom";
import store from "./store";
import {Provider} from "react-redux";
import {createMuiTheme} from "@material-ui/core";
import purple from "@material-ui/core/colors/purple";
import green from "@material-ui/core/colors/green";
import {ThemeProvider} from "@material-ui/styles";
import {SnackbarProvider} from "notistack";

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

const muiTheme = createMuiTheme(
    {
        palette: {
            primary: {
                main: purple[500],
            },
            secondary: {
                main: green[500],
            },
        },
    }
);

ReactDOM.render(
  <React.StrictMode>
      <ThemeProvider theme={muiTheme}>
          <Provider store={store}>
              <HashRouter>
                  <SnackbarProvider maxSnack={5}
                                    dense
                                    anchorOrigin={
                                        {
                                            vertical: 'top',
                                            horizontal: 'right',
                                        }
                                    }
                  >
                      <App />
                  </SnackbarProvider>
              </HashRouter>
          </Provider>
      </ThemeProvider>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
