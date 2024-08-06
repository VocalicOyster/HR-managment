import React, { useEffect, useState } from "react";
import "../css/ShowInterview.css";
import { useNavigate } from "react-router-dom";
import { ApplicantCard } from "./ApplicantCard";

export function ShowInterview() {
  const navigate = useNavigate();
  const token = sessionStorage.getItem("jwt");
  const [interviewList, setInterviewList] = useState([]);

  const pStyle = {
    position: "absolute",
    right: "675px",
    top: "300px",
    fontSize: "20px",
  };
  const imgStyle = {
    width: "250px",
    height: "250px",
    position: "absolute",
    right: "675px",
    top: "50px",
  };

  useEffect(() => {
    if (!token) {
      navigate("/");
    }

    const retrieveInterview = async () => {
      const interview = await fetch(
        "http://localhost:8080/api/interview/list",
        {
          method: "GET",
          headers: new Headers({
            Authorization: "Bearer " + token,
            "Content-Type": "application/json",
          }),
        }
      );

      if (interview.ok) {
        const response = await interview.json();
        if (response.status === 200) {
          setInterviewList(response.data);
        }
      }
    };

    retrieveInterview();
  }, [token, navigate]);

  const deleteInterview = async (intId, appId, date, startTime) => {
    const deletedInt = await fetch(
      `http://localhost:8080/api/interview/${intId}`,
      {
        method: "DELETE",
        headers: new Headers({
          Authorization: "Bearer " + token,
          "Content-Type": "application/json",
        }),
      }
    );

    if (!deletedInt.ok) {
      const responseInt = await deletedInt.json();
      if (responseInt.status === 400) {
        alert(
          responseInt.message || "Errore nella cancellazione del colloquio"
        );
        return;
      }
    }

    const deletedApp = await fetch(`http://localhost:8080/api/app/${appId}`, {
      method: "DELETE",
      headers: new Headers({
        Authorization: "Bearer " + token,
        "Content-Type": "application/json",
      }),
    });

    if (!deletedApp.ok) {
      const responseApp = await deletedApp.json();
      alert(responseApp.message || "Errore nella cancellazione dell'applicant");
      return;
    }

    setInterviewList((prevInterviewList) =>
      prevInterviewList.filter((interview) => interview.id !== intId)
    );

    alert(
      `Colloquio in data ${date} per le ore ${startTime} eliminato con successo`
    );
  };

  const showInterviewReload = (interviewList) => {
    return interviewList.length > 0 ? (
      interviewList.map((interview) => (
        <ApplicantCard
          key={interview.id}
          appId={interview.applicants.id}
          appName={interview.applicants.name}
          fiscalCode={interview.applicants.fiscalCode}
          date={interview.interviewDate}
          startTime={interview.startTime}
          deleteInterview={() =>
            deleteInterview(
              interview.id,
              interview.applicants.id,
              interview.interviewDate,
              interview.startTime
            )
          }
        />
      ))
    ) : (
      <>
        <img src="src\assets\empty.png" alt="" style={imgStyle} />
        <p style={pStyle}>Nessun dipartimento presente!</p>
      </>
    );
  };

  return (
    <div id="containerInterview">{showInterviewReload(interviewList)}</div>
  );
}
