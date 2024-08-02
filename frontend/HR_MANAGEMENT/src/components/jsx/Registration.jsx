import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export function Registration() {
  const [registration, setRegistration] = useState({
    username: "",
    password: "",
    repeatPassword: "",
    name: "",
    surname: "",
    email: "",
    companyName: "",
  });

  const [isOverCompany, setIsOverCompany] = useState(false);
  const [isOverUsername, setIsOverUsername] = useState(false);
  const [isOverName, setIsOverName] = useState(false);
  const [isOverSurname, setIsOverSurname] = useState(false);
  const [isOverEmail, setIsOverEmail] = useState(false);
  const [isOverPassword, setIsOverPassword] = useState(false);
  const [isOverRepeat, setIsOverRepeat] = useState(false);
  const [isOverSubmit, setIsOverSubmit] = useState(false);
  const [equalsPassword, setEqualsPassword] = useState(false);
  const [error, setError] = useState(false);

  const navigate = useNavigate();

  const handleCompanyChange = (e) => {
    setRegistration({
      ...registration,
      companyName: e.target.value,
    });
  };
  const handleUsernameChange = (e) => {
    setRegistration({
      ...registration,
      username: e.target.value,
    });
  };
  const handleNameChange = (e) => {
    setRegistration({
      ...registration,
      name: e.target.value,
    });
  };
  const handleSurnameChange = (e) => {
    setRegistration({
      ...registration,
      surname: e.target.value,
    });
  };
  const handleEmailChange = (e) => {
    setRegistration({
      ...registration,
      email: e.target.value,
    });
  };
  const handlePasswordChange = (e) => {
    setRegistration((prevRegistration) => {
      const newReg = {
        ...prevRegistration,
        password: e.target.value,
      };
      setEqualsPassword(newReg.password !== newReg.repeatPassword);
      return newReg;
    });
  };
  const handleRepeatChange = (e) => {
    setRegistration((prevRegistration) => {
      const newReg = {
        ...prevRegistration,
        repeatPassword: e.target.value,
      };
      setEqualsPassword(newReg.password !== newReg.repeatPassword);
      return newReg;
    });
  };

  const handleIsOverCompany = () => {
    setIsOverCompany(true);
  };
  const handleIsOutCompany = () => {
    setIsOverCompany(false);
  };

  const handleIsOverUsername = () => {
    setIsOverUsername(true);
  };
  const handleIsOutUsername = () => {
    setIsOverUsername(false);
  };

  const handleIsOverName = () => {
    setIsOverName(true);
  };
  const handleIsOutName = () => {
    setIsOverName(false);
  };

  const handleIsOverSurname = () => {
    setIsOverSurname(true);
  };
  const handleIsOutSurname = () => {
    setIsOverSurname(false);
  };

  const handleIsOverEmail = () => {
    setIsOverEmail(true);
  };
  const handleIsOutEmail = () => {
    setIsOverEmail(false);
  };

  const handleIsOverPassword = () => {
    setIsOverPassword(true);
  };
  const handleIsOutPassword = () => {
    setIsOverPassword(false);
  };

  const handleIsOverRepeat = () => {
    setIsOverRepeat(true);
  };
  const handleIsOutRepeat = () => {
    setIsOverRepeat(false);
  };

  const handleIsOverSubmit = () => {
    setIsOverSubmit(true);
  };
  const handleIsOutSubmit = () => {
    setIsOverSubmit(false);
  };

  const registrationContainerStyle = {
    width: "400px",
    height: "800px",
    backgroundColor: "#DBE2EF",
    borderRadius: "20px",
    display: "flex",
    placeItems: "center",
    flexDirection: "column",
    boxShadow: "-5px 5px 15px gray",
    position: "relative",
  };

  const inputFormStyle = {
    marginBottom: "20px",
    marginTop: "20px",
    display: "flex",
    placeItems: "center",
    flexDirection: "column",
  };

  const foreachImgStyle = {
    borderRadius: "100px",
    width: "100px",
    marginTop: "10px",
    marginBottom: "20px",
  };

  const inputCompanyStyle = {
    width: "240px",
    height: "30px",
    border: "none",
    borderLeft: "none",
    borderBottom: error ? "1px solid red" : "none",
    boxShadow: isOverCompany ? "-2px 2px 6px gray" : "",
    backgroundColor: isOverCompany ? "#B4C4DE" : "",
  };

  const inputUsernameStyle = {
    width: "240px",
    height: "30px",
    border: "none",
    borderLeft: "none",
    borderBottom: error ? "1px solid red" : "none",
    boxShadow: isOverUsername ? "-2px 2px 6px gray" : "",
    backgroundColor: isOverUsername ? "#B4C4DE" : "",
  };

  const inputNameStyle = {
    width: "240px",
    height: "30px",
    border: "none",
    borderLeft: "none",
    borderBottom: error ? "1px solid red" : "none",
    boxShadow: isOverName ? "-2px 2px 6px gray" : "",
    backgroundColor: isOverName ? "#B4C4DE" : "",
  };

  const inputSurnameStyle = {
    width: "240px",
    height: "30px",
    border: "none",
    borderLeft: "none",
    borderBottom: error ? "1px solid red" : "none",
    boxShadow: isOverSurname ? "-2px 2px 6px gray" : "",
    backgroundColor: isOverSurname ? "#B4C4DE" : "",
  };

  const inputEmailStyle = {
    width: "240px",
    height: "30px",
    border: "none",
    borderLeft: "none",
    borderBottom: error ? "1px solid red" : "none",
    boxShadow: isOverEmail ? "-2px 2px 6px gray" : "",
    backgroundColor: isOverEmail ? "#B4C4DE" : "",
  };

  const inputPasswordStyle = {
    width: "240px",
    height: "30px",
    border: "none",
    borderLeft: "none",
    borderBottom: equalsPassword || error ? "1px solid red" : "none",
    boxShadow: isOverPassword ? "-2px 2px 6px gray" : "",
    backgroundColor: isOverPassword ? "#B4C4DE" : "",
  };

  const inputRepeatStyle = {
    width: "240px",
    height: "30px",
    border: "none",
    borderLeft: "none",
    borderBottom: equalsPassword ? "1px solid red" : "none",
    boxShadow: isOverRepeat ? "-2px 2px 6px gray" : "",
    backgroundColor: isOverRepeat ? "#B4C4DE" : "",
    marginBottom: "10px",
  };

  const submitStyle = {
    width: "150px",
    height: "40px",
    border: "none",
    backgroundColor: "#B4C4DE",
    boxShadow: isOverSubmit ? "-2px 2px 6px gray" : "",
    position: "absolute",
    bottom: "40px",
  };

  const registrationSending = (e) => {
    e.preventDefault();
    fetch("http://localhost:8080/register", {
      method: "POST",
      headers: new Headers({
        "Content-Type": "application/json",
      }),
      body: JSON.stringify({
        username: registration.username,
        password: registration.password,
        repeatPassword: registration.repeatPassword,
        name: registration.name,
        surname: registration.surname,
        email: registration.email,
        companyName: registration.companyName,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.status === 200) {
          fetch("http://localhost:8080/login", {
            method: "POST",
            headers: new Headers({
              "Content-Type": "application/json",
            }),
            body: JSON.stringify({
              username: registration.username,
              password: registration.password,
            }),
          })
            .then((response) => response.json())
            .then((data) => {
              if (data.internalCode == 200) {
                sessionStorage.setItem("jwt", data.jwt)
                navigate("/main");
              }
            });
            alert("Registrazione avvenuta con successo")
        } else {
          alert("Qualcosa è andato storto");
        }
      });
  };

  return (
    <div style={registrationContainerStyle}>
      <h2 style={{ color: "#3F72AF" }}>REGISTRATI ORA</h2>
      <img
        src="src/assets/ForeachSolutions.jpg"
        alt="foreach Logo"
        style={foreachImgStyle}
      />
      <form onSubmit={registrationSending} method="post" style={inputFormStyle}>
        <label htmlFor="companyName">Nome compagnia</label>
        <input
          type="text"
          name="companyName"
          style={inputCompanyStyle}
          onChange={handleCompanyChange}
          onMouseOver={handleIsOverCompany}
          onMouseOut={handleIsOutCompany}
          placeholder="Inserisci nome compagnia"
        />

        <label htmlFor="username">Username</label>
        <input
          type="text"
          name="username"
          style={inputUsernameStyle}
          onChange={handleUsernameChange}
          onMouseOver={handleIsOverUsername}
          onMouseOut={handleIsOutUsername}
          placeholder="Inserisci Username (almeno 5 caratteri)"
        />

        <label htmlFor="name">Nome</label>
        <input
          type="text"
          name="name"
          id=""
          style={inputNameStyle}
          onChange={handleNameChange}
          onMouseOver={handleIsOverName}
          onMouseOut={handleIsOutName}
          placeholder="Inserisci il tuo nome"
        />

        <label htmlFor="surname">Cognome</label>
        <input
          type="text"
          name="surname"
          style={inputSurnameStyle}
          onChange={handleSurnameChange}
          onMouseOver={handleIsOverSurname}
          onMouseOut={handleIsOutSurname}
          placeholder="Inserisci il tuo cognome"
        />

        <label htmlFor="email">E-mail</label>
        <input
          type="text"
          name="email"
          style={inputEmailStyle}
          onChange={handleEmailChange}
          onMouseOver={handleIsOverEmail}
          onMouseOut={handleIsOutEmail}
          placeholder="ex: xxx@yyy.zzz"
        />

        <label htmlFor="password">Password</label>
        <input
          type="password"
          name="password"
          style={inputPasswordStyle}
          onChange={handlePasswordChange}
          onMouseOver={handleIsOverPassword}
          onMouseOut={handleIsOutPassword}
          placeholder="Inserisci password (almeno 8 caratteri)"
        />

        <label htmlFor="repeat">Ripeti password</label>
        <input
          type="password"
          name="repeat"
          style={inputRepeatStyle}
          onChange={handleRepeatChange}
          onMouseOver={handleIsOverRepeat}
          onMouseOut={handleIsOutRepeat}
          placeholder="Reinserisci password"
        />
        <p style={{ margin: "5px", color: "red" }}>
          {equalsPassword ? "Le passowrd non coincidono" : ""}
        </p>
        <button
          type="submit"
          style={submitStyle}
          onMouseOver={handleIsOverSubmit}
          onMouseOut={handleIsOutSubmit}
        >
          {" "}
          INVIA{" "}
        </button>
        <p style={{ position: "absolute", bottom: "1px", fontSize: "13px" }}>
          Sei gia registrato? Vai al <Link to={"/"}>LOGIN</Link>
        </p>
      </form>
    </div>
  );
}
