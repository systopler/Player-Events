import {withStyles} from "@material-ui/core";
import MuiDialogActions from "@material-ui/core/DialogActions/DialogActions";

const MDialogActions = withStyles(
    theme => (
        {
            root: {
                borderTop: `1px solid ${theme.palette.divider}`,
                margin: 0,
                padding: theme.spacing.unit,
            },
            // action: {
            //     borderRadius: 3,
            //     minHeight: 12,
            //     paddingBottom: 0,
            //     paddingTop: 0,
            //     boxShadow: 'none',
            //     textTransform: 'none',
            //     fontSize: 15,
            //     border: '1px solid rgba(0,0,0,.2)',
            //     backgroundColor: '#ffdb4d',
            //     color: 'black',
            //     fontFamily: [
            //         'YS Text',
            //         'Helvetica Neue',
            //         'Helvetica',
            //         'Arial',
            //         'sans-serif'
            //     ].join(','),
            //     '&:hover': {
            //         backgroundColor: '#ffdb4d',
            //         borderColor: '#ffdb4d',
            //     },
            //     '&:active': {
            //         boxShadow: 'none',
            //         backgroundColor: 'gold',
            //         borderColor: 'gold',
            //     },
            // },
        }
    )
)(MuiDialogActions);

export default MDialogActions;