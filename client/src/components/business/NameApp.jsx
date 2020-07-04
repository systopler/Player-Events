import React from 'react';


function nameApp(props) {
    return (
        <span style={{fontSize: 20, fontFamily: 'Yandex Sans Logotype'}}>
                <span style={{color: 'black'}}>{ props.name }</span>
        </span>
    );
}

export default nameApp;