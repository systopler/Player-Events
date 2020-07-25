import {withStyles} from "@material-ui/core";
import React from "react";
import IconButton from "@material-ui/core/IconButton";
import CloseIcon from '@material-ui/icons/Close';
import MuiDialogTitle from '@material-ui/core/DialogTitle';

const MDialogTitle = withStyles(
    theme => (
        {
            root: {
                borderBottom: `1px solid ${theme.palette.divider}`,
                margin: 0,
                fontFamily: [
                    'YS Text',
                    'Helvetica Neue',
                    'Helvetica',
                    'Arial',
                    'sans-serif'
                ].join(','),
            },
            closeButton: {
                position: 'absolute',
                right: theme.spacing.unit,
                top: theme.spacing.unit,
                color: theme.palette.grey[500],
            },
            label : {
                color : "#000",
                fontSize:'16px',
            }
        }
    )
)(
    (
        { children, classes, onClose }
    ) => {
        return (
            <MuiDialogTitle disableTypography className={classes.root}>
                <span className={classes.label}>{children}</span>
                {
                    onClose &&
                    <IconButton aria-label="Close" className={classes.closeButton} onClick={onClose}>
                        <CloseIcon />
                    </IconButton>
                }
            </MuiDialogTitle>
        );
    }
);


export default MDialogTitle;