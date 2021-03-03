import React, {useEffect} from 'react';
import Grid from "@material-ui/core/Grid";
import {withStyles} from "@material-ui/styles";
import Table from "@material-ui/core/Table";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";
import IconButton from "@material-ui/core/IconButton";
import AddIcon from '@material-ui/icons/Add';
import CreateIcon from '@material-ui/icons/Create';
import RemoveIcon from '@material-ui/icons/Remove';
import {bindActionCreators} from "redux";
import * as organizationsAction from "../actions/OrganizationsAction";
import {connect} from "react-redux";
import {deleteOrganization} from "../../components/api/services/OrganizationsService";


const OrganizationsPage = withStyles (
    theme => (
        {
            a: {
                //width: 221,
                fontSize: 13,
                textDecoration: 'none', /* Отменяем подчеркивание у ссылки */
                black: '#000',
                cursor: 'pointer'
            },
            firstLetter : {
                color : 'red',
                cursor: 'pointer'
            },
            nextLetter : {
                color : '#000',
                cursor: 'pointer'
            }
        }
    )
)(
    (
        {
            classes
            , history
            , organizationsReducers
            , organizationsAction
        }
    ) => {
        useEffect(
            () => {
                if (!organizationsReducers.records && !organizationsReducers.loading && !organizationsReducers.loaded){
                    organizationsAction.load();
                }
            }
        )

        const removeOrganization = (organization) => {

            deleteOrganization(
                organization
            ).then(
                response => {
                    organizationsAction.replace(
                        [
                            ...organizationsReducers.records.filter(
                                item => item.id !== organization.id
                            )
                        ]
                    )
                }
            )
        }

        return (
            <div>
                <Grid container
                      direction="column"
                      justify="center"
                      alignItems="stretch"
                >
                    <Grid item
                          xs={12}
                    >
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <TableCell align="center">
                                        Наименование
                                    </TableCell>
                                    <TableCell align="center">
                                        Тип
                                    </TableCell>
                                    <TableCell align="right">
                                        <IconButton href="#organization">
                                            <AddIcon />
                                        </IconButton>
                                    </TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {
                                    organizationsReducers.records &&
                                    organizationsReducers.records.map(
                                        organization => (
                                            <TableRow>
                                                <TableCell align="center">
                                                    {
                                                        organization.packName
                                                    }
                                                </TableCell>
                                                <TableCell align="center">
                                                    Тип
                                                </TableCell>
                                                <TableCell align="right">
                                                    <Grid container
                                                          justify="space-between"
                                                          alignItems="center"
                                                          direction={"row"}
                                                    >
                                                        <Grid item>
                                                            <IconButton onClick={()=>history.push('/organization/' + organization.id)}>
                                                                <CreateIcon />
                                                            </IconButton>
                                                        </Grid>
                                                        <Grid item>
                                                            <IconButton onClick={()=>removeOrganization(organization)}>
                                                                <RemoveIcon />
                                                            </IconButton>
                                                        </Grid>
                                                    </Grid>
                                                </TableCell>
                                            </TableRow>
                                        )
                                    )
                                }
                            </TableBody>
                        </Table>
                    </Grid>
                </Grid>
            </div>
        );
    }
)

const mapStateToProps = state => {
    return {
        organizationsReducers: state.organizationsReducers,
    }
}

const mapDispatchToProps = dispatch => {
    return {
        organizationsAction: bindActionCreators({...organizationsAction}, dispatch),
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(OrganizationsPage);
