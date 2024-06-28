# HR Managment per aziende


<img src="/images/Designer.png" width="250" height="250" style="border-radius: 10px;" alt="a">

# indice
- [Breve introduzione](#introduzione)
- [Documentazione](#documentazione)
- [Spiegazione dettagliata](#spiegazione-dettagliata)


# Introduzione
#### Gestionale risorse umane rivolto principalmente alle aziende

L'applicazione nasce con lo scopo di essere usato dalle aziende, per una gestione facilitata 
delle risorse umane, (quindi dipendenti, candidati con relative candidature, ecc...)
Troviamo la possibilità di gestire i propri dipendenti aggiungendo un'assunzione del
relativo dipendente, aggiungere per ogni dipendente permessi, giorni di malattia, 
giorni di ferie, straordinari, caricare le buste paga per facilitare la gestione assieme alla parte amministrativa dell'azienda, 
la gestione delle candidature con relativi candidati, candidature e programmazione di giorni e orari dei
colloqui


![general project structure](/images/project%20structure.png) 

![structure](/images/structure.png) 

![controller](/images/controller.png)


# Come iniziare

Per il completo avvio dell'applicazione non serve altro che:

- Avere installato una JDK (ver. 21 o superiore) 
- Avere installato Sql (Consigliato: XAMPP) con un database rinominato "human_resources"
#### Al momento gira in localhost:8080
[Installazione java](https://www.java.com/it/download/manual.jsp)

[Installazione XAMPP](https://www.apachefriends.org/it/index.html)

# Documentazione
Per la documentazione completa, far partire l'applicazione e accedere alla pagina [Swagger](http://localhost:8080/swagger-ui/index.html) 
già presente nel progetto stesso.
Saranno mostrati tutti gli endpoint e sarà anche possibile provarli. È possibile trovare
più info su SpringDoc [qui](https://springdoc.org/)

![Documentazione](/images/documentazione.png)


# Spiegazione dettagliata
Come già anticipato nell'introduzione, quest'applicazione è rivolta alle aziende, 
per la gestione delle loro risorse umane. Principalmente si occupa di gestire

- Dipendenti e relativi: assunzioni, dipartimento di assunzione, permessi, straordinari, giorni di malattia e giorni di ferie 
- Annunci di lavoro con relativi: candidati e relativa valutazione, colloqui per dipartimenti

Le entità utilizzate sono: 

- Applicants: rappresentano i candidati ad un annuncio di lavoro 
- CandidateEvaluations: rappresenta la valutazione dei candidati
- Department: rappresenta un dipartimento dell'azienda 
- Employees: inutile spiegare
- Hiring: rappresenta i dettagli di assunzione di un dipendente
- Interview: rappresenta il colloquio (futuro) di un candidato
- Job announcement: inutile spiegare
- Overtime, Permits, SickDays e Vacation: straordinari, permessi, giorni di malattia e di ferie di un dipendente
- User: rappresenta l'azienda utilizzante


 <i class="fab fa-github-square"></i>


