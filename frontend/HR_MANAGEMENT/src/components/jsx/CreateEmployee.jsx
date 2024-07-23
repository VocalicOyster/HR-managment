import React, { useState } from "react";
import "../css/CreateEmployee.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";

export function CreateEmployee() {
  const token = sessionStorage.getItem("jwt");
  const navigate = useNavigate();

  if (!token) {
    navigate("/");
  }

  const [isHoverName, setIsHoverName] = useState(false);
  const [isHoverAddress, setIsHoverAddress] = useState(false);
  const [isHoverFiscalCode, setIsHoverFiscalCode] = useState(false);
  const [isHoverHiringDate, setIsHoverHiringDate] = useState(false);
  const [isHoverSubmit, setIsHoverSubmit] = useState(false);
  const [profileImages, setProfileImages] = useState(null);
  const [isDisable, setIsDisable] = useState(true);
  const [employee, setEmployee] = useState({
    name: "",
    address: "",
    fiscalCode: "",
    hiringDate: "",
  });
  const placeHolderName = "Inserisci nome completo del dipendente";
  const placeHolderAddress = "Inserisci indirizzo dipendente";
  const placeHolderFiscalCode = "Inserisci codice fiscale";

  const HandleIsOverName = () => {
    setIsHoverName(true);
  };
  const HandleIsOutName = () => {
    setIsHoverName(false);
  };

  const HandleIsOverAddress = () => {
    setIsHoverAddress(true);
  };
  const HandleIsOutAddress = () => {
    setIsHoverAddress(false);
  };

  const HandleIsOverFiscalCode = () => {
    setIsHoverFiscalCode(true);
  };
  const HandleIsOutFiscalCode = () => {
    setIsHoverFiscalCode(false);
  };

  const HandleIsOverHiringDate = () => {
    setIsHoverHiringDate(true);
  };
  const HandleIsOutHiringDate = () => {
    setIsHoverHiringDate(false);
  };

  const HandleMouseOverSub = () => {
    setIsHoverSubmit(true);
  };

  const HandleMouseOutSub = () => {
    setIsHoverSubmit(false);
  };

  const handleFileChange = (e) => {
    if (profileImages == null) {
      setIsDisable(true);
    }
    if (e.target.files.length > 0) {
      setProfileImages(e.target.files[0]);
      setIsDisable(false);
    }
  };

  const HandleChangeName = (e) => {
    setEmployee({
      ...employee,
      name: e.target.value,
    });
  };

  const HandleChangeAddress = (e) => {
    setEmployee({
      ...employee,
      address: e.target.value,
    });
  };

  const HandleCHangeFiscalCode = (e) => {
    setEmployee({
      ...employee,
      fiscalCode: e.target.value,
    });
  };

  const HandleClickSubmit = () => {
    fetch("http://localhost:8080/api/employee/", {
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name: employee.name,
        address: employee.address,
        fiscalCode: employee.fiscalCode,
        hiringDate: employee.hiringDate,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.status == 400 || data.status == 401) {
          alert(data.message);
          return;
        }
        const id = (data.data.id);

        const formData = new FormData();
        formData.append("profileImage", profileImages);

        return fetch(`http://localhost:8080/api/employee/profileImg/${id}`, {
          method: "POST",
          headers: {
            Authorization: `Bearer ${token}`,
          },
          body: formData,
        });
      })
      .then((response) => response.json())
      .then((data) => {
        if (data.status === 400 || data.status === 401) {
          alert(data.message);
          return;
        }
        alert("Dipendente aggiunto con successo");
      });
  };

  const HandleChangeHiringDate = (e) => {
    const date = new Date(e.target.value);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, "0");
    const day = date.getDate().toString().padStart(2, "0");

    const formattedDate = `${day}/${month}/${year}`;

    setEmployee({
      ...employee,
      hiringDate: formattedDate,
    });
  };

  const inputNameStyle = {
    width: "230px",
    height: "30px",
    border: "none",
    boxShadow: isHoverName ? "-2px 2px 6px #112D4E" : "",
    backgroundColor: isHoverName ? "#DBE2EF" : "",
  };

  const inputAddressStyle = {
    width: "230px",
    height: "30px",
    border: "none",
    boxShadow: isHoverAddress ? "-2px 2px 6px #112D4E" : "",
    backgroundColor: isHoverAddress ? "#DBE2EF" : "",
  };

  const inputFiscalCodeStyle = {
    width: "230px",
    height: "30px",
    border: "none",
    boxShadow: isHoverFiscalCode ? "-2px 2px 6px #112D4E" : "",
    backgroundColor: isHoverFiscalCode ? "#DBE2EF" : "",
  };

  const inputHiringDateStyle = {
    width: "230px",
    height: "30px",
    border: "none",
    boxShadow: isHoverHiringDate ? "-2px 2px 6px #112D4E" : "",
    backgroundColor: isHoverHiringDate ? "#DBE2EF" : "",
  };

  const submitStyle = {
    position: "absolute",
    bottom: "90px",
    right: "660px",
    width: "230px",
    height: "50px",
    borderRadius: "0px",
    border: "none",
    boxShadow: isHoverSubmit ? "-2px 2px 6px black" : "",
  };

  return (
    <div id="container">
      <div id="formContainer">
        <h1>Aggiungi un dipendente</h1>
        <img src="src\assets\businessman.png" alt="dep" id="depImg" />
        <ul id="ulEmp">
          <li className="liDep">
            <label htmlFor="employeeName">Nome completo</label>
            <input
              placeholder={placeHolderName}
              type="text"
              name="name"
              style={inputNameStyle}
              onMouseOver={HandleIsOverName}
              onMouseOut={HandleIsOutName}
              onChange={HandleChangeName}
            />
          </li>
          <li className="liDep">
            <label htmlFor="address">Indirizzo</label>
            <input
              placeholder={placeHolderAddress}
              type="text"
              name="address"
              style={inputAddressStyle}
              onMouseOver={HandleIsOverAddress}
              onMouseOut={HandleIsOutAddress}
              onChange={HandleChangeAddress}
            />
          </li>
          <li className="liDep">
            <label htmlFor="fiscalCode">Codice fiscale</label>
            <input
              placeholder={placeHolderFiscalCode}
              type="text"
              name="fiscalCode"
              style={inputFiscalCodeStyle}
              onMouseOver={HandleIsOverFiscalCode}
              onMouseOut={HandleIsOutFiscalCode}
              onChange={HandleCHangeFiscalCode}
            />
          </li>
          <li className="liDep">
            <label htmlFor="hiringDate">Data di assunzione</label>
            <input
              type="date"
              name="hiringDate"
              style={inputHiringDateStyle}
              onMouseOver={HandleIsOverHiringDate}
              onMouseOut={HandleIsOutHiringDate}
              onChange={HandleChangeHiringDate}
            />
          </li>
          <li className="liDep">
            <label htmlFor="profileImage">Immagine di profilo</label>
            <input
              type="file"
              name="profileImgae"
              accept="image/*"
              onChange={handleFileChange}
            />
          </li>
        </ul>
        <button
          type="submit"
          style={submitStyle}
          onMouseOver={HandleMouseOverSub}
          onMouseOut={HandleMouseOutSub}
          onClick={HandleClickSubmit}
          disabled={isDisable}
        >
          Aggiungi dipendente
        </button>
      </div>
    </div>
  );
}
