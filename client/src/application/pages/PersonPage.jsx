import React from 'react';
import {withStyles} from "@material-ui/core";
import Grid from "@material-ui/core/Grid";

const styles = theme => (
    {
        root: {
            display: 'flex',
            height : '100%'
        },
        appBar: {
            zIndex: theme.zIndex.drawer + 1,
            minHeight: '50px',
            height: '50px',
            top: '0px',
            backgroundColor: '#ffffff',
            color: 'black',
            borderBottomColor: 'darkgrey',
            borderBottomWidth: 'thin',
            borderBottomStyle: 'solid',
        },
        content: {
            //flexGrow: 1,
            //marginTop : '50px',
            padding: theme.spacing.unit * 3,
            //width: `calc(100% - (${drawerWidth}px + 48px))`,
        },

        toolbar: theme.mixins.toolbar,
        itemMenu : {
            fontFamily: 'YS Text',
            paddingLeft : 50,
            paddingTop : 0,
            paddingBottom : 0,
            fontSize: 13,
            background : '#f8f8f8'
        },
        activeItemMenu : {
            paddingLeft : 50,
            paddingTop : 0,
            paddingBottom : 0,
            fontSize: 13,
            background : '#ffdb4d'
        }
        , activeLink : {
            //color : '#ffdb4d'
            fontWeight: 700

        }
        , link : {
            color : 'black'
            , fontSize: 13
            , fontFamily: [
                'YS Text',
                'Helvetica Neue',
                'Helvetica',
                'Arial',
                'sans-serif'
            ].join(','),
        }
    }
);

function PersonPage(
    {
        classes
    }
){
    return (
        <div>
            <Grid container
                  direction="row"
                  justify="center"
                  alignItems="center">
                <Grid item
                      xs={3}
                >

                </Grid>
                <Grid item
                      xs={6}
                >

                </Grid>
                <Grid item
                      xs={3}
                >

                </Grid>
            </Grid>
        </div>
    );
}
export default withStyles(styles)(PersonPage);
