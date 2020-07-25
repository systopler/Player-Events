import React, {useEffect, useState} from 'react';
import Grid from "@material-ui/core/Grid";
import {makeStyles} from "@material-ui/styles";
import {findOrganizationAll} from "../../components/api/services/OrganizationsService";
import Table from "@material-ui/core/Table";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";
import IconButton from "@material-ui/core/IconButton";
import AddIcon from '@material-ui/icons/Add';
import CreateIcon from '@material-ui/icons/Create';
import OrganizationDialog from "./dialogs/OrganizationDialog";


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
        history
    }
){
    const classes = useStyles();
    const [organizations, setOrganizations] = useState([]);
    const [loading, setLoading] = useState(false);
    const [loaded, setLoaded] = useState(false);

    useEffect(
        () => {
            setLoading(true);
            if (!loaded){
                if (!loading){
                    console.log("load");
                    findOrganizationAll().then(
                        repsponse => {
                            setOrganizations(repsponse.data);
                            setLoading(false);
                            setLoaded(true);
                        }
                    )
                }
            }
        }
    )


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
                                    <IconButton onClick={()=>history.push('/organization/1')}>
                                        <AddIcon />
                                    </IconButton>
                                </TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {
                                organizations.map(
                                    organization => (
                                        <TableRow>
                                            <TableCell align="center">
                                                Наименование
                                            </TableCell>
                                            <TableCell align="center">
                                                Тип
                                            </TableCell>
                                            <TableCell align="right">
                                                <IconButton>
                                                    <CreateIcon />
                                                </IconButton>
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