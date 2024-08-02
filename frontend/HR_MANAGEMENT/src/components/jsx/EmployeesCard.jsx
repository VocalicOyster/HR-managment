import React, { useState, useEffect } from "react";

export function EmployeesCard({ id, nome, address, fiscalCode, hiringDate, deleteEmployee }) {
  const token = sessionStorage.getItem("jwt");
  const [imageUrl, setImageUrl] = useState("");
  const [isOverDelete, setIsOverDelete] = useState(false);

  const HandleIsOverDelete  = () => {
    setIsOverDelete(true);
  } 
  const HandleIsOutDelete  = () => {
    setIsOverDelete(false);
  } 

  const employeesCardContainer = {
    width: "450px",
    height: "230px",
    backgroundColor: "#F9F7F7",
    borderRadius: "7px",
    display: "flex",
    flexDirection: "column",
    position: "relative",
    border: '1px solid black'
  };

  const imgDivStyle = {
    border: imageUrl === "" ? "2px solid black" : "2px solid #DBE2EF",
    backgroundColor: imageUrl === "" ? "white" : "",
    width: "110px",
    height: "140px",
    position: "absolute",
    top: "50%",
    left: "20px",
    transform: "translateY(-50%)",
  };

  const imgStyle = {
    width: imageUrl === "" ? "64px" : "110px",
    height: imageUrl === "" ? "64px" : "140px",
  };

  const contentStyle = {
    width: "fit-content",
    height: "50%",
    boxSizing: "border-box",
    display: "flex",
    placeItems: "center",
    flexDirection: "column",
    position: "relative",
    left: "140px",
  };

  const pStyleCard = {
    margin: "0px",
    color: "#112D4E",
  };

  const deleteButtonStyle = {
    width: "50px",
    height: "50px",
    padding: "0",
    border: "0",
    position: "absolute",
    left: "380px",
    top: "20px",
    backgroundImage: 'url("src/assets/bin.png")',
    backgroundSize: "cover",
    backgroundPosition: "center",
    borderRadius: '100%',
    boxShadow: isOverDelete ? "-2px 2px 6px #112D4E": ""
  };

  useEffect(() => {
    // Costruire l'URL dell'immagine
    const url = `http://localhost:8080/api/employee/profileImg/${id}`;

    // Funzione per ottenere l'immagine con il token
    const fetchImage = () => {
      fetch(url, {
        method: "GET",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
        .then((response) => response.json())
        .then((data) => {
          if (data.status == 400) {
            console.log(data.message);
            return;
          }

          if (data.status == 401) {
            console.log(data.message);
            return;
          }

          const base64Image = data.data;
          const imageUrl1 = `data:image/png;base64,${base64Image}`;
          setImageUrl(imageUrl1);
        });
    };

    fetchImage();
  }, [token, id]);



  return (
    <div style={employeesCardContainer}>
      <button style={deleteButtonStyle}onMouseOver={HandleIsOverDelete} onMouseOut={HandleIsOutDelete} onClick={deleteEmployee}></button>
      <div
        style={{
          width: "100%",
          height: "50%",
          backgroundImage: 'url("src/assets/patternEmpCard.png")',
        }}
      ></div>
      <div style={imgDivStyle}>
        <img
          src={imageUrl === "" ? "src/assets/error-404.png" : imageUrl}
          alt="id"
          style={imgStyle}
        />
        {imageUrl === "" ? <p> NO IMAGE</p> : ""}
      </div>
      <div style={{ width: "100%", height: "50%", backgroundColor: "#DBE2EF" }}>
        <div style={contentStyle}>
          <p style={pStyleCard}>Nome: {nome}</p>
          <p style={pStyleCard}> Indirizzo: {address}</p>
          <p style={pStyleCard}> CF: {fiscalCode}</p>
          <p style={pStyleCard}> Assunzione: {hiringDate}</p>
        </div>
      </div>
    </div>
  );
}
