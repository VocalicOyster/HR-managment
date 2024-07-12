import React, {useState} from "react";

export function Login() {
    const [isHoverUsername, setIsHoverUsername] = useState(false);
    const [isHoverPassword, setIsHoverPassword] = useState(false);

    const loginContainerStyle = {
        width: '400px',
        height: '650px',
        backgroundColor: '#DBE2EF',
        borderRadius: '20px',
        display: 'flex',
        placeItems: 'center',
        boxShadow: '-5px 5px 15px gray',
        position: 'relative'
    }

    const foreachImgLogo = {
        borderRadius: '100px',
        width: '100px',
        position: 'absolute',
        top: '10px',
        left: '150px'
    }

    const poweredText = {
        fontSize: '10px',
        position: 'absolute',
        bottom: '0px',
        left: '7px',
        color: '#3F72AF'
    }
    const welcomeText = {
        color: '#3F72AF',
        fontSize: '18px',
        position: 'absolute',
        left: '115px',
        top: '100px'
    }

    const inputContainerStyle = {
        width: '250px',
        height: '300px',
        position: 'relative',
        left: '80px',
        display: 'flex',
        placeItems: 'center',
        flexDirection: 'column'
    }

    const inputDivStyle = {
        marginBottom: '20px',
        marginTop: '20px',
        display: 'flex',
        flexDirection: 'column'
    }

    const inputUsernameStyle = {
        width: '240px',
        height: '40px',
        borderTop: 'none',
        borderRight: 'none',
        borderLeft: isHoverUsername ? '1.5px solid gray' : 'none',
        borderBottom: isHoverUsername ? '1.5px solid gray' : 'none',
        backgroundColor: isHoverUsername ? '#3F72AF' : '#F9F7F7',
        transition: 'background-color 0.15s ease-in-out, borderLeft 0.2s ease-in-out, borderBottom 0.2s ease-in-out', 
    }

    const inputPasswordStyle = {
        width: '240px',
        height: '40px',
        borderTop: 'none',
        borderRight: 'none',
        borderLeft: isHoverPassword ? '1.5px solid gray' : 'none',
        borderBottom: isHoverPassword ? '1.5px solid gray' : 'none',
        backgroundColor: isHoverPassword ? '#3F72AF' : '#F9F7F7',
        transition: 'background-color 0.15s ease-in-out, borderLeft 0.2s ease-in-out, borderBottom 0.2s ease-in-out'
    }
    

    const HandleMouseOverUsername = () => {
        setIsHoverUsername(true);
    }
    const HandleMouseOverPassword = () => {
        setIsHoverPassword(true);
    }

    const HandleMouseOutUsername = () => {
        setIsHoverUsername(false);
    }

    const HandleMouseOutPassword = () => {
        setIsHoverPassword(false);
    }


    return ( 
        <div style={loginContainerStyle}> 
            <img src="src\assets\ForeachSolutions.jpg" alt="Foreach Solutions" style={foreachImgLogo}/>

            <h3 style={welcomeText}>Benvenuto! Fai il login</h3>
            <div style={inputContainerStyle}>
               
                <div style={inputDivStyle}>
                    <label htmlFor="username" style={{ textAlign: 'left'}}>Username</label>
                    <input type="text" name="username" placeholder="username" style={inputUsernameStyle} onMouseOver={HandleMouseOverUsername} onMouseOut={HandleMouseOutUsername}/>
                </div>

                <div style={inputDivStyle}>
                    <label htmlFor="password" style={{ textAlign: 'left'}}>Password</label>
                    <input type="password" name="password" placeholder="password" style={inputPasswordStyle} onMouseOver={HandleMouseOverPassword}  onMouseOut={HandleMouseOutPassword}/>
                </div>
        
            </div>

            <p style={poweredText}>powered by ForeachSolutions</p>
        </div>
    )
}