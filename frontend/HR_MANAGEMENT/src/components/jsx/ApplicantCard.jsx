import React, { useEffect, useState } from "react";
import "../css/ApplicantCard.css";
import { useNavigate } from "react-router-dom";

export function ApplicantCard({
  appName,
  fiscalCode,
  date,
  startTime,
  appId,
  intId,
  deleteInterview,
}) {
  const token = sessionStorage.getItem("jwt");
  const navigate = useNavigate();

  const [isOverContainer, setIsOverContainer] = useState(false);
  const [isOverDeleteButton, setIsOverDeleteButton] = useState(false);

  const handleIsOverContainer = () => {
    setIsOverContainer(true);
  };
  const handleIsOutContainer = () => {
    setIsOverContainer(false);
  };

  const handleIsOverButton = () => {
    setIsOverDeleteButton(true);
  };
  const handleIsOutButton = () => {
    setIsOverDeleteButton(false);
  };

  const applicantCard = {
    width: "350px",
    height: "200px",
    backgroundColor: "#3F72AF",
    borderRadius: "10px",
    backgroundImage: "linear-gradient( to top, #3F72AF,#7FA1C3, #F5EDED)",
    display: "flex",
    flexDirection: "column",
    boxShadow: isOverContainer ? "-7px 7px 6px black" : "-2px 2px 6px black",
    transform: isOverContainer ? "scale(1.1)" : "scale(1)",
    transition: "transform 0.5s ease, box-shadow 0.5s ease",
    margin: "35px",
  };

  const pTextInterview = {
    marginLeft: "5px",
    marginBottom: "5px",
    marginTop: "5px",
    width: "fit-content",
  };

  const pTextFiscalCode = {
    marginLeft: "5px",
    marginBottom: "5px",
    marginTop: "5px",
    width: "fit-content",
  };

  const pTextTime = {
    marginLeft: "5px",
    marginBottom: "5px",
    marginTop: "5px",
    width: "fit-content",
  };

  const spanStyle = {
    color: "#112D4E",
    fontSize: "20px",
    fontFamily: "Quicksand",
  };

  const deleteButton = {
    width: "40px",
    height: "40px",
    borderRadius: "30px",
    padding: "0",
    backgroundImage: "url('src/assets/bin.png')",
    backgroundSize: "cover",
    border: "0",
    boxShadow: isOverDeleteButton ? "-5px 5px 6px black" : "-2px 2px 6px black",
    transform: isOverDeleteButton ? "scale(1.1)" : "scale(1)",
    transition: "transform 0.5s ease, box-shadow 0.5s ease",
  };

  useEffect(() => {
    if (!token) {
      navigate("/");
    }
  }, [token, navigate]);

  return (
    <div
      style={applicantCard}
      onMouseOver={handleIsOverContainer}
      onMouseOut={handleIsOutContainer}
    >
      <h3 style={{ fontFamily: "Quicksand" }}>{appName}</h3>
      <p style={pTextFiscalCode}>
        <strong>CF:</strong> <span style={spanStyle}>{fiscalCode}</span>
      </p>
      <p style={pTextInterview}>
        <strong>COLLOQUIO:</strong> <span style={spanStyle}>{date}</span>{" "}
      </p>
      <p style={pTextTime}>
        <strong>ORARIO INIZIO:</strong>{" "}
        <span style={spanStyle}>{startTime}</span>{" "}
      </p>
      <span style={{ margin: "0px" }}>
        <button
          style={deleteButton}
          onMouseOver={handleIsOverButton}
          onMouseOut={handleIsOutButton}
          onClick={deleteInterview}
        ></button>
      </span>
    </div>
  );
}
