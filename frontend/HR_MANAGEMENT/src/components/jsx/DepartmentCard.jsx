import React from "react";

export function DepartmentCard( {depName, depDesc} ) {
    const cardStyle = {
        width: '150px',
        height: '200px',
        backgroundColor: '#3F72AF',
        borderRadius: '20px',
        display: 'flex',
        placeItems: 'center',
        flexDirection: 'column',
        border: '1px solid gray',
        boxShadow: '-2px 2px 6px black',
    }
    const nameStyle = {
        width: '130px',
        height: '85px',
        border: '1px solid black',
        backgroundColor: '#DBE2EF',
    }

    const prova = () => {
        alert('sssssss')
    }
    return (
        <button style={cardStyle} onClick={prova}>
            <img src="src\assets\dep.png" alt="sadaw" width={130} height={100}/>
            <div style={nameStyle}>
                <h6 style={{margin: '0px'}}>{depName}</h6>
                <p style={{margin: '0px', marginTop: '2px', fontSize: '12px'}}>{depDesc}</p>
            </div>
        </button>
    )
}