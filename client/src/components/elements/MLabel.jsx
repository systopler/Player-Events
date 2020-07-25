import React from 'react';
import withStyles from "@material-ui/core/styles/withStyles";
import classNames from 'classnames';

const MLabel = withStyles(
    theme => (
        {
            allStyle : {
                fontFamily: [
                    'YS Text',
                    'Helvetica Neue',
                    'Helvetica',
                    'Arial',
                    'sans-serif',
                ].join(','),
                fontSize: 13,
                overflow: "hidden",
                textOverflow: "ellipsis",
            },
            labelStyle: {
                color : "#929292"
            },
            linkStyle : {
                color : '#00c',
                cursor: 'pointer',
                textDecoration: 'underline',
            },
            required : {
                color : 'red',
            },
        }
    )
)
(
    (
        {
            classes, required, onClick, style, children
        }
    ) =>
    {
        let labelClasses = classNames(classes.allStyle, onClick ? classes.linkStyle : classes.labelStyle);

        return (
            <span className={labelClasses}
                  onClick = { onClick }
                  style = { style }
            >
                <span style={{ overflowY : 'hidden', textOverflow : 'ellipsis' }}>
                    {
                        children
                    }
                </span>
                {
                    required
                    && <span className={classes.required}>*</span>
                }
            </span>
        );
    }
);

export default MLabel;