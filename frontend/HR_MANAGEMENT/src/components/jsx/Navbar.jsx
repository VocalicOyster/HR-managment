import React, { useState } from "react";

export function Navbar( { showCreateDep, showDepts, showCreateEmp, showEmp, showCreateApp, showInterview }) {
  const [isActiveDept, setIsActiveDept] = useState(false);
  const [isActiveEmp, setIsActiveEmp] = useState(false);
  const [isActiveShowDept, setIsActiveShowDept] = useState(false);
  const [isActiveShowEmp, setIsActiveShowEmp] = useState(false);
  const [isActiveApp, setIsActiveApp] = useState(false)
  const [isActiveShowInterview, setIsActiveShowInterview] = useState(false);

  const sideButtonStyleDep = {
    height: "50px",
    width: "250px",
    border: "0px",
    backgroundColor: isActiveDept ? "#112D4E" : "#3F72AF",
    borderBottom: '1px solid black'
  };

  const sideButtonStyleEmployee = {
    height: "50px",
    width: "250px",
    border: "0px",
    backgroundColor: isActiveEmp ? "#112D4E" : "#3F72AF",
    borderBottom: '1px solid black'
  };

   const sideButtonStyleShowDept = {
    height: "50px",
    width: "250px",
    border: "0px",
    backgroundColor: isActiveShowDept ? "#112D4E" : "#3F72AF",
    borderBottom: '1px solid black'
   }

   const sideButtonStyleShowEmp = {
    height: "50px",
    width: "250px",
    border: "0px",
    backgroundColor: isActiveShowEmp ? "#112D4E" : "#3F72AF",
    borderBottom: '1px solid black'
   }

   const sideButtonStyleShowApp = {
    height: "50px",
    width: "250px",
    border: "0px",
    backgroundColor: isActiveApp ? "#112D4E" : "#3F72AF",
    borderBottom: '1px solid black'
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
    backgroundColor: "#3F72AF",
    boxShadow: '2px -2px 6px #112D4E',
    border: '1px solid black'
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

  const sideButtonShowInterview = {
    height: "50px",
    width: "250px",
    border: "0px",
    backgroundColor: isActiveShowInterview ? "#112D4E" : "#3F72AF",
    borderBottom: '1px solid black'
  }

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

  const onMouseOverSideButtonApp = () => {
    setIsActiveApp(true);
  }

  const onMouseOutSideButtonApp = () => {
    setIsActiveApp(false)
  }

  const onMouseOverSideButtonInt = () => {
    setIsActiveShowInterview(true);
  };
  const onMouseOutSideButtonInt = () => {
    setIsActiveShowInterview(false);
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
              onClick={showCreateDep}
            >
              AGGIUNGI DIPARTIMENTO
            </button>
          </li>
          <li>
            <button
              style={sideButtonStyleShowDept}
              onMouseOver={onMouseOverSideButtonShowDept}
              onMouseLeave={onMouseLeaveSideButtonShowDept}
              onClick={showDepts}
            >
              MOSTRA DIPARTIMENTI
            </button>
          </li>
          <li>
            <button
              style={sideButtonStyleEmployee}
              onMouseOver={onMouseOverSideButtonEmp}
              onMouseLeave={onMouseLeaveSideButtonEmp}
              onClick={showCreateEmp}
            >
              AGGIUNGI DIPENDENTI
            </button>
          </li>
          <li>
            <button
              style={sideButtonStyleShowEmp}
              onMouseOver={onMouseOverSideButtonShowEmp}
              onMouseLeave={onMouseLeaveSideButtonShowEmp}
              onClick={showEmp}
            >
              MOSTRA DIPENDENTI
            </button>
          </li>
          <li>
            <button
              style={sideButtonStyleShowApp}
              onMouseOver={onMouseOverSideButtonApp}
              onMouseLeave={onMouseOutSideButtonApp}
              onClick={showCreateApp}
            >
              AGGIUNGI CANDIDATO
            </button>
          </li>
          <li>
            <button
            style={sideButtonShowInterview}
            onMouseOver={onMouseOverSideButtonInt}
            onMouseOut={onMouseOutSideButtonInt}
            onClick={showInterview}
            >
              MOSTRA COLLOQUI
            </button>
          </li>
        </ul>

        <button style={profileButtonStyle}> </button>
      </div>
    </>
  );
}
