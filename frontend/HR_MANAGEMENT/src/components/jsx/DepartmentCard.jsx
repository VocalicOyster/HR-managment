import React, { useState } from "react";

export function DepartmentCard({ depName, depDesc,  deleteDepartment}) {

    
    const [isHoverDelete, setIsHoverDelete] = useState(false);
    const [isHoverUpdate, setIsHoverUpdate] = useState(false);

    const HandleOverDelete = () => {
        setIsHoverDelete(true);
    }

    const HandleOutDelete = () => {
        setIsHoverDelete(false);
    }

    const HandleOverUpdate = () => {
        setIsHoverUpdate(true);
    }

    const HandleOutUpdate = () => {
        setIsHoverUpdate(false);
    }

    

  const cardStyle = {
    width: "150px",
    height: "230px",
    backgroundColor: "#3F72AF",
    borderRadius: "20px",
    display: "flex",
    placeItems: "center",
    flexDirection: "column",
    border: "1px solid gray",
    boxShadow: "-2px 2px 6px black",
  };
  const nameStyle = {
    width: "130px",
    height: "85px",
    border: "1px solid black",
    backgroundColor: "#DBE2EF",
  };

  const deleteBottonStyle = {
    backgroundImage: 'url("src/assets/bin.png")',
    backgroundSize: "cover",
    width: "35px",
    height: "35px",
    border: "none",
    borderRadius: "35px",
    left: "-30px",
    top: "265px",
    padding: '0px',
    boxShadow: isHoverDelete ? "-2px 2px 6px black" : ""
  };

  const updateBottonStyle = {
    backgroundImage: 'url("src/assets/changes.png")',
    backgroundSize: "cover",
    width: "35px",
    height: "35px",
    border: "none",
    borderRadius: "35px",
    left: "30px",
    top: "232px",
    padding: '0px',
    marginLeft: '5px',
    boxShadow: isHoverUpdate ? "-2px 2px 6px black" : "",
    backgroundColor: 'white'
  };

  const buttonContainer = {
    width: '130px',
    height: '35px',
    paddingTop: '4px'
  }

  return (
    <div style={cardStyle}>
      <img src="src\assets\dep.png" alt="sadaw" width={130} height={100} />
      <div style={nameStyle}>
        <h6 style={{ margin: "0px" }}>{depName}</h6>
        <p style={{ margin: "0px", marginTop: "2px", fontSize: "12px" }}>
          {depDesc}
        </p>
      </div>
      <div style={buttonContainer}>
        <button style={deleteBottonStyle} onMouseOver={HandleOverDelete} onMouseOut={HandleOutDelete} onClick={deleteDepartment}> </button>
        <button style={updateBottonStyle} onMouseOver={HandleOverUpdate} onMouseOut={HandleOutUpdate}> </button>
      </div>
    </div>
  );
}
