import React from 'react';
import {MuiThemeProvider} from '@material-ui/core/styles';
import TextField from "@material-ui/core/TextField";


import {createMuiTheme} from "@material-ui/core";

const theme = createMuiTheme(
    {
        overrides: {
            MuiInput: {
                underline: {
                    borderBottom: '0.5px dashed rgba(0,0,0,.2)',

                    '&:hover': {
                        borderBottom: '0.5px dashed rgba(0,0,0,.2)',
                        //borderBottom: 'none !important',
                    },

                    '&:before': {
                        borderBottom: 'none !important',
                    },
                    '&:after': {
                        borderBottom: 'none !important',
                    },
                    '&:focus': {
                        //border: "0.5px solid #ffdb4d",
                        borderBottom: 'none !important',
                    },
                },
                input : {
                    minHeight : 18.909,
                    paddingLeft: '3px',
                    paddingTop: '6px',
                    paddingBottom: '6px',
                    paddingRight: '0px',
                    borderRadius: 0,
                    fontSize: 13,
                    // Use the system font instead of the default Roboto font.
                    fontFamily: [
                        'YS Text',
                        'Helvetica Neue',
                        'Helvetica',
                        'Arial',
                        'sans-serif',
                    ].join(','),
                    '&:focus': {
                        border: "0.5px solid #ffdb4d",
                    },
                }
            }
        }
    }
);

class MTextField extends React.Component{

    static defaultProps = {
        type: 'text',
        fullWidth : false,
        multiline : false,
        rows : 1,
    }

    onChange = (event) => {
        if (this.props && this.props.onChange){
            this.props.onChange((this.props && this.props.type && this.props.type === 'date') ? new Date (event.target.value) : event.target.value);
        }
    }

    render(){
        const { onChange, children,  ...others } = this.props;
        return (
            <MuiThemeProvider theme={theme}>
                <TextField onChange = { this.onChange }
                           {...others}
                >
                    {children}
                </TextField>
            </MuiThemeProvider>
        );
    }
}

export default MTextField;
