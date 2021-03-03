import React, {useEffect, useState} from 'react';
import Dialog from "@material-ui/core/Dialog";
import MDialogTitle from "../../../components/elements/MDialogTitle";
import MDialogContent from "../../../components/elements/MDialogContent";
import Grid from "@material-ui/core/Grid";
import {makeStyles, withStyles} from "@material-ui/styles";
import MLabel from "../../../components/elements/MLabel";
import MTextField from "../../../components/elements/MTextField";
import MDialogActions from "../../../components/elements/MDialogActions";
import MButton from "../../../components/elements/MButton";
import Autocomplete from "@material-ui/lab/Autocomplete";
import {bindActionCreators} from "redux";
import * as organizationTypesAction from "../../actions/OrganizationTypesAction";
import {connect} from "react-redux";
import {useSnackbar} from "notistack";
import {saveOrganization} from "../../../components/api/services/OrganizationsService";

const OrganizationNameDialog = withStyles(
    theme => (
        {
            rowStyle : {
                width : '100%'
            },
        }

    )
)(
    (
        {
            classes
            , organizationTypesReducers
            , organizationTypesAction
            , organization
            , onReplace
            , onClose
        }
    )=>{
        const { enqueueSnackbar } = useSnackbar();

        const [packName, setPackName] = useState(organization.packName);
        const [fullName, setFullName] = useState(organization.fullName);
        const [organizationType, setOrganizationType] = useState(organization.organizationType);

        const [wait, setWait] = useState(false);

        const handleSave = () => {
            if (!packName){
                enqueueSnackbar("Укажите краткое наименование организации", {variant  : 'error'})
                return;
            }
            if (!organizationType){
                enqueueSnackbar("Укажите тип организации", {variant  : 'error'})
                return;
            }
            saveOrganization(
                {
                    ...organization
                    , packName
                    , fullName
                    , organizationType
                }
            ).then(
                response => {
                    if (onReplace) onReplace(response.data);
                    enqueueSnackbar('Организация сохранена', {variant  : 'success'})
                    setWait(false);
                    if (onClose) onClose();
                }
                , error => {
                    enqueueSnackbar(error.response.data.message, {variant  : 'error'})
                    setWait(false);
                }
            )
        }

        return (
            <Dialog open
                    fullWidth
                    maxWidth={"sm"}
            >
                <MDialogTitle onClose={onClose}>
                    Организация
                </MDialogTitle>
                <MDialogContent>
                    <Grid container
                          justify="center"
                          alignItems="center"
                          direction={"column"}
                    >
                        <Grid item xs={12} className={classes.rowStyle}>
                            <Grid container
                                  justify="space-between"
                                  alignItems="center"
                                  direction={"row"}
                            >
                                <Grid item xs={4}>
                                    <MLabel required>
                                        Краткое наименование
                                    </MLabel>
                                </Grid>
                                <Grid item xs={8}>
                                    <MTextField fullWidth
                                                value={packName}
                                                onChange={(value) => setPackName(value)}

                                    />
                                </Grid>
                            </Grid>
                        </Grid>
                        <Grid item xs={12} className={classes.rowStyle}>
                            <Grid container
                                  justify="space-between"
                                  alignItems="center"
                                  direction={"row"}
                            >
                                <Grid item xs={4}>
                                    <MLabel>
                                        Полное наименование
                                    </MLabel>
                                </Grid>
                                <Grid item xs={8}>
                                    <MTextField fullWidth
                                                value={fullName}
                                                onChange={(value) => setFullName(value)}
                                    />
                                </Grid>
                            </Grid>
                        </Grid>
                        <Grid item xs={12} className={classes.rowStyle}>
                            <Grid container
                                  justify="space-between"
                                  alignItems="center"
                                  direction={"row"}
                            >
                                <Grid item xs={4}>
                                    <MLabel required>
                                        Тип
                                    </MLabel>
                                </Grid>
                                <Grid item xs={8}>
                                    <Autocomplete fullWidth
                                                  renderInput={
                                                      (params) => (
                                                          <MTextField fullWidth
                                                                      {...params}
                                                          />
                                                      )
                                                  }
                                                  options={organizationTypesReducers.records}
                                                  getOptionLabel={(value) => value.meaning}
                                                  onChange={
                                                      (event, value) => {
                                                          setOrganizationType(value)
                                                      }
                                                  }
                                                  value = {organizationType}
                                    />
                                </Grid>
                            </Grid>
                        </Grid>
                    </Grid>
                </MDialogContent>
                <MDialogActions>
                    <MButton onClick={handleSave}
                    >
                        Сохранить
                    </MButton>
                </MDialogActions>
            </Dialog>
        )
    }
)

const mapStateToProps = state => {
    return {
        organizationTypesReducers: state.organizationTypesReducers,
    }
}

const mapDispatchToProps = dispatch => {
    return {
        organizationTypesAction: bindActionCreators({...organizationTypesAction}, dispatch),
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(OrganizationNameDialog);
