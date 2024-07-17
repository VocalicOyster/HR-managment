import React, { useEffect } from "react";
import "../css/ShowDepartments.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { DepartmentCard } from "./DepartmentCard";

export function ShowDepartments() {
  const navigate = useNavigate();
  const [depts, setDepts] = useState([]);
  const token = sessionStorage.getItem("jwt");

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
            if (data.data.length === 0) {
              alert("Nessun dipartimento presente. Aggiungine uno!");
            } else {
              setDepts(data.data);
            }
          });

      getDepts();
    }
  }, [token, navigate]);

  console.log(depts);

  return (
    <div id="containerDepts">
      <div id='cardContainer'>
        {depts.length > 0 ? (
          depts.map((dept) => (
            <DepartmentCard
              key={dept.id}
              depName={dept.name}
              depDesc={dept.description}
            />
          ))
        ) : (
          <p>Caricamento dei dipartimenti...</p>
        )}
      </div>
    </div>
  );
}
