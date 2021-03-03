import {makeStyles, withStyles} from "@material-ui/styles";
import React, {useEffect, useState} from "react";
import {findOrganizationById} from "../../../components/api/services/OrganizationsService";
import Grid from "@material-ui/core/Grid";
import MLabel from "../../../components/elements/MLabel";
import MTextField from "../../../components/elements/MTextField";
import {bindActionCreators} from "redux";
import * as organizationTypesAction from "../../actions/OrganizationTypesAction";
import * as organizationsAction from "../../actions/OrganizationsAction";
import {connect} from "react-redux";
import Autocomplete from "@material-ui/lab/Autocomplete";
import Avatar from "@material-ui/core/Avatar";
import PhotoCameraIcon from '@material-ui/icons/PhotoCamera';
import IconButton from "@material-ui/core/IconButton";
import EditIcon from '@material-ui/icons/Edit';
import Divider from "@material-ui/core/Divider";
import OrganizationNameDialog from "./OrganizationNameDialog";

const styles =
    theme => (
        {
            a: {
                //width: 221,
                fontSize: 13,
                textDecoration: 'none', /* Отменяем подчеркивание у ссылки */
                black: '#000',
                cursor: 'pointer'
            },
            avatar : {
                width: theme.spacing(20),
                height: theme.spacing(20),
            },
            input: {
                display: 'none',
            },
            box : {
                position: 'relative',
                width: '100%'
            },
            '&:before' :{
                content: "",
                display: "block",
                paddingTop: "100%"           /* initial ratio of 1:1*/
            }
        }
    );

function OrganizationsPage(
    {
        classes
        , match
        , organizationTypesReducers
        , organizationTypesAction
        , organizationsReducers
        , organizationsAction
    }
){
    const [organization, setOrganization] = useState();
    const [loading, setLoading] = useState(false);
    const [loaded, setLoaded] = useState(false);
    const [isOpenOrganizationNameDialog, setOpenOrganizationNameDialog] = useState(false);

    useEffect(
        () => {
            if (!organizationTypesReducers.loaded && !organizationTypesReducers.loading){
                organizationTypesAction.load();
            }
            if (match.params.id){
                setLoading(true);
                if (!loaded){
                    if (!loading){
                        console.log("load");
                        findOrganizationById(match.params.id).then(
                            repsponse => {
                                setOrganization(repsponse.data);
                                setLoading(false);
                                setLoaded(true);
                            }
                        )
                    }
                }
            } else {
                setOrganization({})
            }
        }
        , [match.params.id]
    )

    const handleReplace = (value) => {
        if (!organizationsReducers.records){
            organizationsAction.replace(
                [
                    organization
                ]
            )
        } else {
            if (organization.id){
                organizationsAction.replace(
                    [
                        ...organizationsReducers.records.map(
                            item => item.id === organization.id ? organization : item
                        )
                    ]
                )
            } else {
                organizationsAction.replace(
                    [
                        ...organizationsReducers.records
                        , organization
                    ]
                )
            }
        }
        setOrganization(value);
    }

    console.log("match", match);

    return (
        <div>
            {
                organization &&
                <Grid container
                      direction="column"
                      justify="center"
                      alignItems="stretch"
                >
                    <Grid item
                          xs={12}
                    >
                        <Grid container
                              direction="row"
                              justify="center"
                              alignItems="stretch"
                              spacing={3}
                        >
                            <Grid item
                                  xs={4}
                            >
                                <Grid container
                                      direction="column"
                                      justify="center"
                                      alignItems="center"
                                >
                                    <Grid item
                                    >
                                        <Avatar className={classes.avatar} />
                                    </Grid>
                                    <Grid item>
                                        <input accept="image/*" className={classes.input} id="icon-button-file" type="file" />
                                        <label htmlFor="icon-button-file">
                                            <IconButton color="primary" aria-label="upload picture" component="span">
                                                <PhotoCameraIcon />
                                            </IconButton>
                                        </label>
                                    </Grid>
                                </Grid>
                            </Grid>
                            <Grid item
                                  xs={8}
                            >
                                <Grid container
                                      direction="column"
                                      justify="center"
                                      alignItems="stretch"
                                >
                                    <Grid item
                                    >
                                        <Grid container
                                              direction="column"
                                              justify="flex-end"
                                              alignItems="flex-end"
                                        >
                                            <Grid item>
                                                <IconButton onClick={()=>setOpenOrganizationNameDialog(true)}>
                                                    <EditIcon fontSize={"small"} />
                                                </IconButton>
                                            </Grid>
                                        </Grid>
                                    </Grid>
                                    <Grid item
                                    >
                                        <Grid container
                                              direction="row"
                                              justify="flex-start"
                                              alignItems="stretch"
                                        >
                                            <Grid item
                                                  xs={4}
                                            >
                                                <MLabel>Наименование организации</MLabel>
                                            </Grid>
                                            <Grid item
                                                  xs={8}
                                            >
                                                {
                                                    organization.packName
                                                }
                                            </Grid>
                                        </Grid>
                                    </Grid>
                                    <Grid item
                                    >
                                        <Grid container
                                              direction="row"
                                              justify="flex-start"
                                              alignItems="stretch"
                                        >
                                            <Grid item
                                                  xs={4}
                                            >
                                                <MLabel>Поное наименование организации</MLabel>
                                            </Grid>
                                            <Grid item
                                                  xs={8}
                                            >
                                                {
                                                    organization.fullName
                                                }
                                            </Grid>
                                        </Grid>
                                    </Grid>
                                    <Grid item
                                    >
                                        <Grid container
                                              direction="row"
                                              justify="center"
                                              alignItems="stretch"
                                        >
                                            <Grid item
                                                  xs={4}
                                            >
                                                <MLabel>Тип организации</MLabel>
                                            </Grid>
                                            <Grid item
                                                  xs={8}
                                            >
                                                <Autocomplete fullWidth
                                                              renderInput={
                                                                  (params) => (
                                                                      <MTextField fullWidth
                                                                                  {...params}
                                                                                  style={{paddingTop : "0px", paddingBottom : "0px"}}
                                                                      />
                                                                  )
                                                              }
                                                              options={organizationTypesReducers.records}
                                                              getOptionLabel={(value) => value.meaning}
                                                              onChange={
                                                                  (event, value) => {
                                                                      //console.log("value", value);
                                                                      setOrganization(
                                                                          {
                                                                              ...organization
                                                                              , organizationType : value
                                                                          }
                                                                      )
                                                                  }
                                                              }
                                                              value = {organization.organizationType ? organization.organizationType : null}
                                                />

                                            </Grid>
                                        </Grid>
                                    </Grid>
                                </Grid>
                            </Grid>
                        </Grid>
                    </Grid>
                    <Grid item>
                        <Divider />
                    </Grid>
                </Grid>
            }
            {
                isOpenOrganizationNameDialog &&
                <OrganizationNameDialog organization={organization}
                                        onReplace={(value) => handleReplace(value)}
                                        onClose={()=>setOpenOrganizationNameDialog(false)}
                />
            }
        </div>
    );
}

const mapStateToProps = state => {
    return {
        organizationTypesReducers: state.organizationTypesReducers,
        organizationsReducers: state.organizationsReducers,
    }
}

const mapDispatchToProps = dispatch => {
    return {
        organizationTypesAction: bindActionCreators({...organizationTypesAction}, dispatch),
        organizationsAction: bindActionCreators({...organizationsAction}, dispatch),
    }
}



export default withStyles(styles, { withTheme: true })(connect(mapStateToProps, mapDispatchToProps)(OrganizationsPage));
