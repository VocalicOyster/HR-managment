import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../css/CreateDepartment.css";

export function CreateDepartment() {
  const navigate = useNavigate();
  const placeHolderName = "Inserisci il nome del dipartimento";
  const placeholderDescription =
    "Inserisci una descrizione per il tuo dipartimento";

  const [isHoverName, setIsHoverName] = useState(false);
  const [isHoverDesc, setIsHoverDesc] = useState(false);
  const [isHoverSubmit, setIsHoverSubmit] = useState(false);
  const [department, setDepartment] = useState({ name: "", description: "" });

  const token = sessionStorage.getItem("jwt");

  if (!token) {
    navigate("/");
  }

  const HandleMouseOverName = () => {
    setIsHoverName(true);
  };

  const HandleMouseOutName = () => {
    setIsHoverName(false);
  };

  const HandleMouseOverDesc = () => {
    setIsHoverDesc(true);
  };
  const HandleMouseOutDesc = () => {
    setIsHoverDesc(false);
  };
  const HandleMouseOverSub = () => {
    setIsHoverSubmit(true);
  };
  const HandleMouseOutSub = () => {
    setIsHoverSubmit(false);
  };
  const HandleOnChangeName = (e) => {
    setDepartment({
      ...department,
      name: e.target.value,
    });
  };

  const HandleOnChangeDescription = (e) => {
    setDepartment({
      ...department,
      description: e.target.value,
    });
  };

  const inputNameStyle = {
    width: "230px",
    height: "30px",
    border: "none",
    boxShadow: isHoverName ? "2px 2px 6px #112D4E" : "",
    backgroundColor: isHoverName ? "#DBE2EF" : "",
  };

  const descriptionStyle = {
    resize: "none",
    border: "none",
    boxShadow: isHoverDesc ? "2px 2px 6px #112D4E" : "",
    backgroundColor: isHoverDesc ? "#DBE2EF" : "",
  };

  const submitStyle = {
    position: "absolute",
    bottom: "225px",
    right: "660px",
    width: "230px",
    height: "50px",
    borderRadius: "0px",
    border: "none",
    boxShadow: isHoverSubmit ? "-2px 2px 6px black" : "",
  };

  const createDep = () => {
    fetch("http://localhost:8080/api/department/", {
      method: "POST",
      headers: new Headers({
        Authorization: "Bearer " + token,
        "Content-Type": "application/json",
      }),
      body: JSON.stringify({
        name: department.name,
        description: department.description,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.status == 200) {
          alert("DIPAARTIMENTO " + department.name + " CREATO CON SUCCESSO");
        }
        if (data.status == 400) {
          alert("Impossibile creare un nuovo dipartimento ");
          console.log(data.message);
        }
      });
  };

  return (
    <>
      <div id="container">
        <div id="formContainer">
          <h1>Aggiungi un dipartimento</h1>
          <img src="src\assets\dep.png" alt="dep" id="depImg" />
          <ul id="ulDep">
            <li className="liDep">
              <label htmlFor="department">Nome dipartimento</label>
              <input
                onChange={HandleOnChangeName}
                style={inputNameStyle}
                type="text"
                name="department"
                placeholder={placeHolderName}
                onMouseOver={HandleMouseOverName}
                onMouseOut={HandleMouseOutName}
              />
            </li>
            <li className="liDep">
              <label htmlFor="description">Descrizione</label>
              <textarea
                onChange={HandleOnChangeDescription}
                style={descriptionStyle}
                cols={33}
                rows={4}
                type="textarea"
                name="description"
                placeholder={placeholderDescription}
                onMouseOver={HandleMouseOverDesc}
                onMouseOut={HandleMouseOutDesc}
              />
            </li>
          </ul>

          <button
            type="submit"
            onClick={createDep}
            style={submitStyle}
            onMouseOver={HandleMouseOverSub}
            onMouseOut={HandleMouseOutSub}
          >
            Crea dipartimento
          </button>
        </div>
      </div>
    </>
  );
}
