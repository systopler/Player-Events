import React from 'react';
import './App.css';
import {withStyles} from "@material-ui/core";
import AccountCircleIcon from '@material-ui/icons/AccountCircle';
import Grid from "@material-ui/core/Grid";
import Avatar from "@material-ui/core/Avatar";
import Typography from "@material-ui/core/Typography";
import Toolbar from "@material-ui/core/Toolbar";
import NameApp from "../components/business/NameApp";
import Routes from "../routing/Routes";


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

function App(
    {
        classes
    }
) {
  return (
    <div className="App">
      <div position="fixed" className={classes.appBar}>
          <Toolbar className="ToolBar">
              <Grid container
                    direction={"row"}
                    justify="space-between"
                    alignItems="center"
                    spacing={2}
              >
                  <Grid item>
                      <Typography variant="caption" color="inherit" style={{ flexGrow : 1, textAlign : 'left' }}>
                          <NameApp name="Хоккей" />
                      </Typography>
                  </Grid>
                  <Grid item>
                  </Grid>
                  <Grid item>
                      {
                          <Avatar style={{height: 48, width: 48, cursor: 'pointer'}}
                                  aria-haspopup="true"
                                  aria-controls="menu-list-grow"
                          >
                              <AccountCircleIcon />
                          </Avatar>
                      }
                  </Grid>
              </Grid>
          </Toolbar>
      </div>
        <main className={classes.content}>
            <Routes />
        </main>
    </div>
  );
}

export default withStyles(styles)(App);
