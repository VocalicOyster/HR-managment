import React, { useState } from "react";

export function Navbar() {
  const [isActiveDept, setIsActiveDept] = useState(false);
  const [isActiveEmp, setIsActiveEmp] = useState(false);
  const [isActiveShowDept, setIsActiveShowDept] = useState(false);
  const [isActiveShowEmp, setIsActiveShowEmp] = useState(false);

  const sideButtonStyleDep = {
    height: "50px",
    width: "250px",
    border: "0px",
    backgroundColor: isActiveDept ? "#112D4E" : "#3F72AF",
  };

  const sideButtonStyleEmployee = {
    height: "50px",
    width: "250px",
    border: "0px",
    backgroundColor: isActiveEmp ? "#112D4E" : "#3F72AF",
  };

   const sideButtonStyleShowDept = {
    height: "50px",
    width: "250px",
    border: "0px",
    backgroundColor: isActiveShowDept ? "#112D4E" : "#3F72AF",
   }

   const sideButtonStyleShowEmp = {
    height: "50px",
    width: "250px",
    border: "0px",
    backgroundColor: isActiveShowEmp ? "#112D4E" : "#3F72AF",
   }

  const navbarStyle = {
    display: "flex",
    placeItems: "center",
    flexDirection: "column",
    position: "absolute",
    top: "0",
    left: "0",
    width: "300px",
    height: "100vh",
    backgroundColor: "#DBE2EF",
  };

  const profileButtonStyle = {
    border: "0px",
    borderRadius: "30px",
    backgroundImage: 'url("src/assets/user.png")',
    height: "35px",
    width: "35px",
    backgroundSize: "cover",
    backgroundPosition: "center",
  };

  const onMouseOverSideButtonDept = () => {
    setIsActiveDept(true);
  };
  const onMouseLeaveSideButtonDept = () => {
    setIsActiveDept(false);
  };
  const onMouseOverSideButtonEmp = () => {
    setIsActiveEmp(true);
  };
  const onMouseLeaveSideButtonEmp = () => {
    setIsActiveEmp(false);
  };

  const onMouseOverSideButtonShowDept = () => {
    setIsActiveShowDept(true);
  };

  const onMouseLeaveSideButtonShowDept = () => {
    setIsActiveShowDept(false);
  };

  const onMouseOverSideButtonShowEmp = () => {
    setIsActiveShowEmp(true);
  };

  const onMouseLeaveSideButtonShowEmp = () => {
    setIsActiveShowEmp(false);
  };

  return (
    <>
      <div style={navbarStyle}>
        <h2> HR MANAGEMENT</h2>
        <ul style={{ listStyleType: "none", padding: "0px" }}>
          <li>
            <button
              style={sideButtonStyleDep}
              onMouseOver={onMouseOverSideButtonDept}
              onMouseLeave={onMouseLeaveSideButtonDept}
            >
              AGGIUNGI DIPARTIMENTO
            </button>
          </li>
          <li>
            <button
              style={sideButtonStyleShowDept}
              onMouseOver={onMouseOverSideButtonShowDept}
              onMouseLeave={onMouseLeaveSideButtonShowDept}
            >
              MOSTRA DIPARTIMENTI
            </button>
          </li>
          <li>
            <button
              style={sideButtonStyleEmployee}
              onMouseOver={onMouseOverSideButtonEmp}
              onMouseLeave={onMouseLeaveSideButtonEmp}
            >
              AGGIUNGI DIPENDENTI
            </button>
          </li>
          <li>
            <button
              style={sideButtonStyleShowEmp}
              onMouseOver={onMouseOverSideButtonShowEmp}
              onMouseLeave={onMouseLeaveSideButtonShowEmp}
            >
              MOSTRA DIPENDENTI
            </button>
          </li>
        </ul>

        <button style={profileButtonStyle}> </button>
      </div>
    </>
  );
}
