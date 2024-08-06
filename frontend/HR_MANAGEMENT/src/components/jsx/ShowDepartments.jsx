import React, { useEffect } from "react";
import "../css/ShowDepartments.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { DepartmentCard } from "./DepartmentCard";

export function ShowDepartments() {
  const navigate = useNavigate();
  const [depts, setDepts] = useState([]);
  const token = sessionStorage.getItem("jwt");

  const pStyle = {
    position: "absolute",
    right: "675px",
    top: '300px',
    fontSize: '20px'
  }
  const imgStyle = {
    width: '250px',
    height: '250px',
    position: 'absolute',
    right: "675px",
    top: '50px'

  }

  useEffect(() => {
    if (!token) {
      navigate("/");
    } else {
      const getDepts = () =>
        fetch("http://localhost:8080/api/department/list", {
          method: "GET",
          headers: new Headers({
            Authorization: "Bearer " + token,
            "Content-Type": "application/json",
          }),
        })
          .then((response) => {
            if (!response.ok) {
              throw new Error("Errore durante il recupero dei dipartimenti.");
            }
            return response.json();
          })

          .then((data) => {
            setDepts(data.data);
          });

      getDepts();
    }
  }, [token, navigate]);



  const showDepReload = (depts) => {
    return depts.length > 0 ? (
      depts.map((dept) => (
        <DepartmentCard
          key={dept.id}
          depName={dept.name}
          depDesc={dept.description}
          depId={dept.id}
          deleteDepartment={() => deleteDepartment(dept.id)}
        />
      ))
    ) : (
      <>
        <img src="src\assets\empty.png" alt="" style={imgStyle}/>
        <p style={pStyle}>Nessun dipartimento presente!</p>
      </>
    );
  };

  const deleteDepartment = (depId) => {
    fetch(`http://localhost:8080/api/department/${depId}`, {
      method: "DELETE",
      headers: new Headers({
        Authorization: "Bearer " + token,
        "Content-Type": "application/json",
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.status == 200) {
          alert("Departimento eliminato con successo");
          setDepts((prevDepts) =>
            prevDepts.filter((dept) => dept.id !== depId)
          );
        } else alert(data.message);
      });
  };


  return (
    <div id="containerDepts">
      <div id="cardContainer">{showDepReload(depts)}</div>
    </div>
  );
}
