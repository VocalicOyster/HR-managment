import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export function Login() {
  const [isHoverUsername, setIsHoverUsername] = useState(false);
  const [isHoverPassword, setIsHoverPassword] = useState(false);
  const [isHoverButton, setIsHoverButton] = useState(false);
  const [user, setUser] = useState({ username: "", password: "" });
  const [error, setError] = useState(false);
  const [errorMess, setErrorMess] = useState("");

  const loginContainerStyle = {
    width: "400px",
    height: "650px",
    backgroundColor: "#DBE2EF",
    borderRadius: "20px",
    display: "flex",
    placeItems: "center",
    boxShadow: "-5px 5px 15px gray",
    position: 'relative',
    left: '750px'
  };

  const foreachImgLogo = {
    borderRadius: "100px",
    width: "100px",
    position: "absolute",
    top: "60px",
    left: "145px",
  };

  const poweredText = {
    fontSize: "10px",
    position: "absolute",
    bottom: "0px",
    left: "7px",
    color: "#3F72AF",
  };
  const welcomeText = {
    color: "#3F72AF",
    fontSize: "18px",
    position: "absolute",
    left: "110px",
    top: "150px",
  };

  const inputContainerStyle = {
    width: "250px",
    height: "300px",
    position: "relative",
    top: "90px",
    left: "80px",
    display: "flex",
    placeItems: "center",
    flexDirection: "column",
  };

  const inputDivStyle = {
    marginBottom: "20px",
    marginTop: "20px",
    display: "flex",
    flexDirection: "column",
  };

  const inputUsernameStyle = {
    width: "240px",
    height: "40px",
    border: "none",
    borderLeft: "none",
    borderBottom: error ? "1px solid red" : "none",
    boxShadow: isHoverUsername ? "-2px 2px 6px gray" : "",
    backgroundColor: isHoverUsername ? "#B4C4DE" : "#F9F7F7",
    transition:
      "background-color 0.15s ease-in-out, boxShadow 0.3s ease-in-out",
  };

  const inputPasswordStyle = {
    width: "240px",
    height: "40px",
    border: "none",
    borderBottom: error ? "1px solid red" : "none",
    boxShadow: isHoverPassword ? "-2px 2px 6px gray" : "",
    backgroundColor: isHoverPassword ? "#B4C4DE" : "#F9F7F7",
    transition:
      "background-color 0.15s ease-in-out, boxxShadow 0.3s ease-in-out",
  };

  const submitButton = {
    width: "150px",
    height: "40px",
    position: "absolute",
    top: "220px",
    left: "50px",
    border: "none",
    backgroundColor: "#B4C4DE",
    boxShadow: isHoverButton ? "-2px 2px 6px gray" : "",
  };

  const errorStyle = {
    position: "absolute",
    top: "230px",
    left: "90px",
    color: "red",
  };

  const registerP = {
    position: 'relative',
    top: '220px',
    right: '115px',
    fontSize: '12px'
  }

  const HandleMouseOverUsername = () => {
    setIsHoverUsername(true);
  };
  const HandleMouseOverPassword = () => {
    setIsHoverPassword(true);
  };

  const HandleMouseOutUsername = () => {
    setIsHoverUsername(false);
  };

  const HandleMouseOutPassword = () => {
    setIsHoverPassword(false);
  };

  const HandleMouseOverButton = () => {
    setIsHoverButton(true);
  };
  const HandleMouseOutButton = () => {
    setIsHoverButton(false);
  };

  const SessionStorage = {
    set: function (item, value) {
      sessionStorage.setItem(item, value);
    },
    get: function (item) {
      return sessionStorage.getItem(item);
    },
  };

  const navigate = useNavigate();

  const HandleLogin = (e) => {
    e.preventDefault();
    fetch("http://localhost:8080/login", {
      method: "POST",
      headers: new Headers({
        "Content-Type": "application/json",
      }),
      body: JSON.stringify({
        username: user.username,
        password: user.password,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.internalCode == 400) {
          setError(true);
          setErrorMess("Username o password non validi");
        }
        if (data.internalCode == 200) {
          SessionStorage.set("jwt", data.jwt);
          navigate("/main");
        }
      });
  };

  const HandleUsernameChange = (e) => {
    setUser({
      ...user,
      username: e.target.value,
    });
  };

  const HandlePasswordChange = (e) => {
    setUser({
      ...user,
      password: e.target.value,
    });
  };

  const navigateRegistartion = (e) => {
    e.preventDefault();
    navigate("/registration");
  }

  return (
    <div style={loginContainerStyle}>
      <img
        src="src\assets\ForeachSolutions.jpg"
        alt="Foreach Solutions"
        style={foreachImgLogo}
      />

      <h3 style={welcomeText}>Benvenuto! Fai il login</h3>
      <form style={inputContainerStyle} method="post"  onSubmit={HandleLogin}>
        <div style={inputDivStyle}>
          <label htmlFor="username" style={{ textAlign: "left" }}>
            Username
          </label>
          <input
            value={user.username}
            type="text"
            name="username"
            placeholder="username"
            style={inputUsernameStyle}
            onMouseOver={HandleMouseOverUsername}
            onMouseOut={HandleMouseOutUsername}
            onChange={HandleUsernameChange}
          />
        </div>

        <div style={inputDivStyle}>
          <label htmlFor="password" style={{ textAlign: "left" }}>
            Password
          </label>
          <input
            value={user.password}
            type="password"
            name="password"
            placeholder="password"
            style={inputPasswordStyle}
            onMouseOver={HandleMouseOverPassword}
            onMouseOut={HandleMouseOutPassword}
            onChange={HandlePasswordChange}
          />
        </div>

        <button
        type="submit"
        style={submitButton}
        onMouseOver={HandleMouseOverButton}
        onMouseOut={HandleMouseOutButton}
      >
        LOGIN
      </button>
      </form>

      <p style={registerP}> Non sei ancora registrato? <a href="/registration" onClick={navigateRegistartion}>REGISTARTI ORA</a></p>
      <p style={errorStyle}>{errorMess}</p>

      <p style={poweredText}>powered by ForeachSolutions</p>
    </div>
  );
}
