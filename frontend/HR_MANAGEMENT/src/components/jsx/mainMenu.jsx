import React, { useEffect, useState } from "react";
import { Navbar } from "./Navbar";
import { useNavigate } from "react-router-dom";
import { CreateDepartment } from "./CreateDepartment";
import { ShowDepartments } from "./ShowDepartments";


export function MainMenu() {
  const navigate = useNavigate();
  const [showCreateDep, setShowCreateDep] = useState(false);
  const [showDepts, setShowDept] = useState(false);

  const HandleShowCreateDep = () => {
    if(showDepts == true) {
      setShowDept(false);
    }
    setShowCreateDep(!showCreateDep);
  }
  const HandleShowDepts = () => {
    if(showCreateDep == true) {
      setShowCreateDep(false);
    }
    setShowDept(!showDepts);
  }

  useEffect(() => {
    if (!sessionStorage.getItem("jwt")) {
      navigate("/");
    }
  }, [navigate]);

  return (
    <>
      <Navbar 
      showCreateDep={HandleShowCreateDep}
      showDepts={HandleShowDepts}></Navbar>
      {showCreateDep && <CreateDepartment></CreateDepartment>}
      {showDepts && <ShowDepartments></ShowDepartments>}
    </>
  );
}
