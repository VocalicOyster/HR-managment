import React, { useEffect, useState } from "react";
import "../css/CreateApplicant.css";
import { useNavigate } from "react-router-dom";

export function CreateApplicant() {
  const navigate = useNavigate();
  const token = sessionStorage.getItem("jwt");
  const [isOverNameInput, setIsOverNameInput] = useState(false);
  const [isOverFiscalInput, setIsOverFiscalInput] = useState(false);
  const [isOverDateInput, setIsOverInputDate] = useState(false);
  const [isOverTimeInput, setIsOverTimeInput] = useState(false);
  const [isOverSubmit, setIsOverSubmit] = useState(false);
  const [dataError, setDataError] = useState(false);
  const [applicant, setApplicant] = useState({
    appName: "",
    fiscalCode: "",
  });
  const [interview, setInterview] = useState({
    applicants_id: 0,
    interviewDate: "",
    startTime: "",
  });

  const initialTimes = [
    "10:00",
    "10:30",
    "11:00",
    "11:30",
    "12:00",
    "12:30",
    "13:00",
    "13:30",
    "14:00",
    "14:30",
    "15:00",
    "15:30",
    "16:00",
    "16:30",
  ];

  function controlDate(dataStringa, selTime) {
    const [giorno, mese, anno] = dataStringa.split("/").map(Number);
    const data = new Date(anno, mese - 1, giorno);

  
    const [giornoSel, meseSel, annoSel] = selTime.split("/").map(Number);
    const dataSel = new Date(annoSel, meseSel - 1, giornoSel);
   
    if (dataSel < data) {
      alert("Non puoi selezionare una data inferiore a quella di oggi");
      return;
    }
  }

  const [availableTimes, setAvailableTimes] = useState(initialTimes);
  const [selectedTime, setSelectedTime] = useState("");

  const handleIsOverName = () => {
    setIsOverNameInput(true);
  };
  const handleIsOutName = () => {
    setIsOverNameInput(false);
  };

  const handleIsOverFiscal = () => {
    setIsOverFiscalInput(true);
  };
  const handleIsOutFiscal = () => {
    setIsOverFiscalInput(false);
  };

  const handleIsOverDate = () => {
    setIsOverInputDate(true);
  };
  const handleIsOutDate = () => {
    setIsOverInputDate(false);
  };

  const handleIsOverTime = () => {
    setIsOverTimeInput(true);
  };
  const handleIsOutTime = () => {
    setIsOverTimeInput(false);
  };

  const handleIsOverSubmit = () => {
    setIsOverSubmit(true);
  };
  const handleIsOutSubmit = () => {
    setIsOverSubmit(false);
  };

  const appImg = {
    width: "50px",
    height: "50px",
  };

  const sendButton = {
    border: "0px",
    width: "155px",
    height: "40px",
    backgroundColor: isOverSubmit ? "#B4C4DE" : "white",
    boxShadow: isOverSubmit ? "-2px 2px 6px gray" : "",
  };

  const nameInput = {
    width: "230px",
    height: "30px",
    border: "0px",
    boxShadow: isOverNameInput ? "-2px 2px 6px gray" : "",
    backgroundColor: isOverNameInput ? "#B4C4DE" : "",
  };

  const fiscalCodeInput = {
    width: "230px",
    height: "30px",
    border: "0px",
    boxShadow: isOverFiscalInput ? "-2px 2px 6px gray" : "",
    backgroundColor: isOverFiscalInput ? "#B4C4DE" : "",
  };

  const dateInput = {
    width: "230px",
    height: "30px",
    boxShadow: isOverDateInput ? "-2px 2px 6px gray" : "",
    backgroundColor: isOverDateInput ? "#B4C4DE" : "",
    textAlign: "center",
    border: dataError ? "1px solid red" : '0px'
  };

  const timeInput = {
    width: "230px",
    height: "30px",
    border: "0px",
    boxShadow: isOverTimeInput ? "-2px 2px 6px gray" : "",
    backgroundColor: isOverTimeInput ? "#B4C4DE" : "white",
    textAlign: "center",
    marginBottom: "20px",
  };

  const sendCreateApplicant = async (e) => {
    e.preventDefault();

    const date = new Date();
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, "0");
    const day = date.getDate().toString().padStart(2, "0");

    const formattedDate = `${day}/${month}/${year}`;

    const [giorno, mese, anno] = formattedDate.split("/").map(Number);
    const data = new Date(anno, mese - 1, giorno);

  
    const [giornoSel, meseSel, annoSel] = interview.interviewDate.split("/").map(Number);
    const dataSel = new Date(annoSel, meseSel - 1, giornoSel);
   
    if (dataSel < data) {
      setDataError(true);
      alert("Non puoi selezionare una data inferiore a quella di oggi");
      return;
    }

    const controlApp = await fetch("http://localhost:8080/api/app/control", {
      method: "POST",
      headers: new Headers({
        Authorization: "Bearer " + token,
        "Content-Type": "application/json",
      }),
      body: JSON.stringify({
        name: applicant.appName,
        fiscalCode: applicant.fiscalCode,
      }),
    });

    if (controlApp.status === 400) {
      alert("Candidato già presente nei database");
      return;
    }

    const controlInterview = await fetch(
      "http://localhost:8080/api/interview/control",
      {
        method: "POST",
        headers: new Headers({
          Authorization: "Bearer " + token,
          "Content-Type": "application/json",
        }),
        body: JSON.stringify({
          applicants_id: interview.applicants_id,
          interviewDate: interview.interviewDate,
          startTime: interview.startTime,
        }),
      }
    );

    if (controlInterview.status === 400) {
      alert("Un colloquio è già fissato per quell'ora");
      return;
    }

    const createApplicant = await fetch("http://localhost:8080/api/app/", {
      method: "POST",
      headers: new Headers({
        Authorization: "Bearer " + token,
        "Content-Type": "application/json",
      }),
      body: JSON.stringify({
        name: applicant.appName,
        fiscalCode: applicant.fiscalCode,
      }),
    });

    const createdApp = await createApplicant.json();
    const newApplicantsId = createdApp.data.id;

    setInterview((prevInterview) => ({
      ...prevInterview,
      applicants_id: newApplicantsId,
    }));
    const createInterview = await fetch(
      "http://localhost:8080/api/interview/",
      {
        method: "POST",
        headers: new Headers({
          Authorization: "Bearer " + token,
          "Content-Type": "application/json",
        }),
        body: JSON.stringify({
          applicants_id: newApplicantsId,
          interviewDate: interview.interviewDate,
          startTime: selectedTime,
        }),
      }
    );
    setAvailableTimes((prevTimes) =>
      prevTimes.filter((time) => time !== selectedTime)
    );
    setSelectedTime("");

    if (createInterview.status === 200) {
      alert("Candidato aggiunto con successo");
    }
  };

  const handleChangeName = (e) => {
    setApplicant({
      ...applicant,
      appName: e.target.value,
    });
  };

  const handleChangeFiscal = (e) => {
    setApplicant({
      ...applicant,
      fiscalCode: e.target.value,
    });
  };

  const handleChangeDate = (e) => {
    setDataError(false);
    const date = new Date(e.target.value);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, "0");
    const day = date.getDate().toString().padStart(2, "0");

    const formattedDate = `${day}/${month}/${year}`;

    const url =
      "http://localhost:8080/api/interview/time?date=" + formattedDate;
    fetch(url, {
      method: "POST",
      headers: new Headers({
        Authorization: "Bearer " + token,
        "Content-Type": "application/json",
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        setAvailableTimes(data.data);
      });

    setInterview({
      ...interview,
      interviewDate: formattedDate,
    });
  };

  if (!token) {
    navigate("/");
  }

  return (
    <div id="container">
      <div id="formContainerApp">
        <h1>Aggiungi info candidato</h1>
        <img src="src/assets/application.png" alt="sadas" style={appImg} />
        <form method="post" onSubmit={sendCreateApplicant}>
          <ul id="ulApp">
            <li className="liApp">
              <label htmlFor="name">Nome completo</label>
              <input
                style={nameInput}
                type="text"
                name="name"
                onMouseOver={handleIsOverName}
                onMouseOut={handleIsOutName}
                onChange={handleChangeName}
              />
            </li>
            <li className="liApp">
              <label htmlFor="fiscalCode">Codice fiscale</label>
              <input
                style={fiscalCodeInput}
                type="text"
                name="fiscalCode"
                onMouseOver={handleIsOverFiscal}
                onMouseOut={handleIsOutFiscal}
                onChange={handleChangeFiscal}
              />
            </li>
            <li className="liApp">
              <label htmlFor="interviewDate">Data colloquio</label>
              <input
                style={dateInput}
                type="date"
                name="interviewDate"
                onMouseOver={handleIsOverDate}
                onMouseOut={handleIsOutDate}
                onChange={handleChangeDate}
              />
            </li>
            <li className="liApp">
              <label htmlFor="times">Seleziona un orario</label>
              <select
                style={timeInput}
                name="times"
                value={selectedTime}
                onChange={(e) => setSelectedTime(e.target.value)}
                onMouseOver={handleIsOverTime}
                onMouseOut={handleIsOutTime}
              >
                <option value="">--- Seleziona ---</option>
                {availableTimes.map((time, index) => (
                  <option key={index} value={time}>
                    {time}
                  </option>
                ))}
              </select>
            </li>
            <li></li>
            <button
              type="submit"
              style={sendButton}
              onMouseOver={handleIsOverSubmit}
              onMouseOut={handleIsOutSubmit}
            >
              Crea candidato
            </button>
          </ul>
        </form>
      </div>
    </div>
  );
}
