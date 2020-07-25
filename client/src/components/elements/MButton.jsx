import React from 'react';
import Button from '@material-ui/core/Button';
import {withStyles} from "@material-ui/core";

const MButton = withStyles(
    theme => (
        {
            button: {
                borderRadius: 3,
                minHeight: 12,
                paddingBottom: 0,
                paddingTop: 0,
                boxShadow: 'none',
                textTransform: 'none',
                fontSize: 15,
                border: '1px solid rgba(0,0,0,.2)',
                backgroundColor: '#ffdb4d',
                color: 'black',
                fontFamily: [
                    'YS Text',
                    'Helvetica Neue',
                    'Helvetica',
                    'Arial',
                    'sans-serif'
                ].join(','),
                '&:hover': {
                    backgroundColor: '#ffdb4d',
                    borderColor: '#ffdb4d',
                },
                '&:active': {
                    boxShadow: 'none',
                    backgroundColor: 'gold',
                    borderColor: 'gold',
                },
            },
        }
    )
)(
    (
        {
            classes, children, ...others
        }
    ) => {
        return (
            <Button variant="outlined"
                    disableRipple
                    className={classes.button}
                    { ...others }
            >
                { children }
            </Button>
        );
    }
);

export default MButton;