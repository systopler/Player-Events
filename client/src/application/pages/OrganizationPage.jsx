import {makeStyles} from "@material-ui/styles";
import React, {useEffect, useState} from "react";
import {findOrganizationAll, findOrganizationById} from "../../components/api/services/OrganizationsService";
import Grid from "@material-ui/core/Grid";
import Table from "@material-ui/core/Table";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import IconButton from "@material-ui/core/IconButton";
import AddIcon from "@material-ui/icons/Add";
import TableBody from "@material-ui/core/TableBody";
import CreateIcon from "@material-ui/icons/Create";
import OrganizationDialog from "./dialogs/OrganizationDialog";
import MLabel from "../../components/elements/MLabel";
import MTextField from "../../components/elements/MTextField";

const useStyles = makeStyles(
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
);

export default function OrganizationsPage(
    {
        match
    }
){
    const classes = useStyles();
    const [organization, setOrganization] = useState();
    const [loading, setLoading] = useState(false);
    const [loaded, setLoaded] = useState(false);

    useEffect(
        () => {
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
            }
        }
    )

    console.log("match", match);

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
                    <Grid container
                          direction="row"
                          justify="center"
                          alignItems="stretch"
                    >
                        <Grid item
                              xs={4}
                        >

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
                                      xs={12}
                                >
                                    <Grid container
                                          direction="row"
                                          justify="center"
                                          alignItems="stretch"
                                    >
                                        <Grid item
                                              xs={4}
                                        >
                                            <MLabel>Краткое наименование</MLabel>
                                        </Grid>
                                        <Grid item
                                              xs={8}
                                        >
                                            <MTextField fullWidth />
                                        </Grid>
                                    </Grid>
                                </Grid>
                                <Grid item
                                      xs={12}
                                >
                                    <Grid container
                                          direction="row"
                                          justify="center"
                                          alignItems="stretch"
                                    >
                                        <Grid item
                                              xs={4}
                                        >
                                            <MLabel>Полное наименование</MLabel>
                                        </Grid>
                                        <Grid item
                                              xs={8}
                                        >
                                            <MTextField fullWidth />
                                        </Grid>
                                    </Grid>
                                </Grid>
                            </Grid>
                        </Grid>
                    </Grid>
                </Grid>
            </Grid>
        </div>
    );
}