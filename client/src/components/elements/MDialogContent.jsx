import {withStyles} from "@material-ui/core";
import MuiDialogContent from '@material-ui/core/DialogContent';

const MDialogContent = withStyles(
    theme => (
        {
            root: {
                margin: 0,
                padding: theme.spacing(2),
                fontSize: '13px',
                fontFamily: [
                    'YS Text',
                    'Helvetica Neue',
                    'Helvetica',
                    'Arial',
                    'sans-serif'
                ].join(','),
            },
        }
    )
)(MuiDialogContent);

export default MDialogContent;