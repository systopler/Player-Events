import React, {useEffect, useState} from 'react';
import Dialog from "@material-ui/core/Dialog";
import MDialogTitle from "../../../components/elements/MDialogTitle";
import MDialogContent from "../../../components/elements/MDialogContent";
import Grid from "@material-ui/core/Grid";
import {makeStyles} from "@material-ui/styles";
import MLabel from "../../../components/elements/MLabel";
import MTextField from "../../../components/elements/MTextField";
import MDialogActions from "../../../components/elements/MDialogActions";
import MButton from "../../../components/elements/MButton";


const useStyles = makeStyles(
    theme => (
        {
            rowStyle : {
                width : '100%'
            },
        }
    )
);
export default function OrganizationDialog(
    {
        onClose
    }
){
    const classes = useStyles();

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
                                <MLabel>
                                    Краткое наименование
                                </MLabel>
                            </Grid>
                            <Grid item xs={8}>
                                <MTextField fullWidth

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

                                />
                            </Grid>
                        </Grid>
                    </Grid>
                </Grid>
            </MDialogContent>
            <MDialogActions>
                <MButton onClick={onClose}
                >
                    Закрыть
                </MButton>
            </MDialogActions>
        </Dialog>
    )
}