import React, { useEffect, useState } from "react";
import { Navbar } from "./Navbar";
import { useNavigate } from "react-router-dom";
import { CreateDepartment } from "./CreateDepartment";
import { ShowDepartments } from "./ShowDepartments";
import { CreateEmployee } from "./CreateEmployee";
import { ShowEmployees } from "./ShowEmployees";
import { CreateApplicant } from "./CreateApplicant";


export function MainMenu() {
    const navigate = useNavigate();
    const [showCreateDep, setShowCreateDep] = useState(false);
    const [showDepts, setShowDept] = useState(false);
    const [showCreateEmp, setShowCreateEmp] = useState(false);
    const [showEmployees, setShowEmployees] = useState(false);
    const [showCreateApp, setShowCreateApp] = useState(false);
    
    const HandleShowCreateDep = () => {
        if(showDepts == true) {
            setShowDept(false);
        }
        if(showCreateEmp == true) {
            setShowCreateEmp(false)
        }
        if(showEmployees == true) {
            setShowEmployees(false);
        }
        if(showCreateApp == true) {
            setShowCreateApp(false)
        }
        setShowCreateDep(!showCreateDep);
    }
    
    const HandleShowDepts = () => {
        if(showEmployees == true) {
            setShowEmployees(false);
        }
        if(showCreateDep == true) {
            setShowCreateDep(false);
        }
        if(showCreateEmp == true) {
            setShowCreateEmp(false)
        }
        if(showCreateApp == true) {
            setShowCreateApp(false)
        }
        setShowDept(!showDepts);
    }
    
    const HandleShowCreateEmp = () => {
        if(showEmployees == true) {
            setShowEmployees(false);
        }
        if(showDepts == true) {
            setShowDept(false);
        }
        if(showCreateDep == true) {
            setShowCreateDep(false);
        }
        if(showCreateApp == true) {
            setShowCreateApp(false)
        }
        setShowCreateEmp(!showCreateEmp)
    }
    
    const HandleShowEmp = () => {
        if(showDepts == true) {
            setShowDept(false);
        }
        if(showCreateDep == true) {
            setShowCreateDep(false);
        }
        if(showCreateEmp == true) {
            setShowCreateEmp(false)
        }
        if(showCreateApp == true) {
            setShowCreateApp(false)
        }
        setShowEmployees(!showEmployees)
    }
    
    const HandleShowCreateApp = () => {
        if(showDepts == true) {
            setShowDept(false);
        }
        if(showCreateDep == true) {
            setShowCreateDep(false);
        }
        if(showCreateEmp == true) {
            setShowCreateEmp(false)
        }
        if(showEmployees == true) {
            setShowEmployees(false);
        }
        setShowCreateApp(!showCreateApp)
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
        showDepts={HandleShowDepts}
        showCreateEmp={HandleShowCreateEmp}
        showEmp={HandleShowEmp}
        showCreateApp={HandleShowCreateApp}></Navbar>
        {showCreateDep && <CreateDepartment></CreateDepartment>}
        {showDepts && <ShowDepartments></ShowDepartments>}
        {showCreateEmp && <CreateEmployee></CreateEmployee>}
        {showEmployees && <ShowEmployees></ShowEmployees>}
        {showCreateApp && <CreateApplicant></CreateApplicant>}
        </>
    );
}
