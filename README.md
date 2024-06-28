# HR Management per aziende

<a href="https://www.instagram.com/foreachsolutions/">
<img src="/images/ForeachSolutions.jpg" width="150" height="150" style="border-radius: 2px;" alt="a">
</a>


# Indice
- [Breve introduzione](#introduzione)
- [Documentazione](#documentazione)
- [Spiegazione dettagliata](#spiegazione-dettagliata)
- [Stack tecnologico](#stack-tecnologico)
- [Community](#community)
- [Come contribuire](#come-contribuire)
- [Road map](#road-map)


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
per la gestione delle loro risorse umane. Principalmente si occupa di gestire:

- Dipendenti e relativi: assunzioni, dipartimento di assunzione, permessi, straordinari, giorni di malattia e giorni di ferie 
- Annunci di lavoro con relativi: candidati e relativa valutazione, colloqui per dipartimenti

#### Da notare il fatto che sarà possibile la registrazione di più aziende
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


È presente anche una funzione di seeding, che permette il riempimento iniziale del database con dei valori preimpostati,
avendo così la possibilità di eseguire dei test iniziali


# Stack tecnologico

Per lo sviluppo di quest'applicazione sono state usate tecnologie varie. Abbiamo:

<figure>
<img src="https://logowik.com/content/uploads/images/java1655.logowik.com.webp" width="48" height="48" style="border-radius: 10px" alt="JAVA"/>
<figcaption>JAVA</figcaption>
</figure>


<figure>
    <img src="/images/icons8-spring-boot-48.png" alt="SpringBoot" />
    <figcaption>SpringBoot</figcaption>
</figure>


<figure>
<img src="images/springsecurity.png" width="48" height="48" style="border-radius: 10px"/>
<figcaption>Spring Security</figcaption>
</figure>


<figure>
<img src="https://upload.wikimedia.org/wikipedia/commons/8/87/Sql_data_base_with_logo.png" height="48" style="border-radius: 10px"/>
<figcaption>Json Web Token</figcaption>
</figure>


<figure>
<img src="images/springsecurity.png" width="48" height="48" style="border-radius: 10px"/>
<figcaption>Spring Security</figcaption>
</figure>


<figure>
<img src="https://seeklogo.com/images/J/json-web-tokens-jwt-io-logo-C003DEC47A-seeklogo.com.png" width="48" height="48" style="border-radius: 10px"/>
<figcaption>Json Web Token</figcaption>
</figure>


<figure>
<img src="images/tomcat.png"width="48" height="48" alt="TOMCAT"/>
<figcaption>Tomcat Web Server</figcaption>
</figure>


<figure>
    <img src="https://miro.medium.com/v2/resize:fit:400/0*jba3dz1j64rfhl5i.jpg" width="48" height="48" style="border-radius: 10px"  alt="Hibernate" />
    <figcaption>Hibernate</figcaption>
</figure>


<figure>
<img src="https://help.apiary.io/images/swagger-logo.png" width="48" height="48" alt="SWAGGER"/>
<figcaption>Spring Doc OpenAPI</figcaption>
</figure>


<figure>
<img src="images/Apache_Feather_Logo.png" width="48" height="48" style="border-radius: 10px"/>
<figcaption>Apache Common</figcaption>
</figure>

# Community

Per qualsiasi idea di miglioramento per il progetto, scrivi su gitter a "#hr@VocalicOyster:gitter.im"

# Come contribuire

Per qualsiasi tipo di contribuzione, create un branch (source branch: DEV) con il formato "feat/nomeFeature" 
e create le PR

# Road Map

Queste sono le feature che vorrei implementare successivamente. Questa qui presente è soltanto un prima versione
e, ovviamente, potrebbero essere presenti bug e malfunzionamenti

- Aggiunzione della gestione (download, upload ed eliminazione) dei CV dei candidati

Altre idee future verranno inserite qui


# AUTORE

Mi chiamo Giovanni, su GitHub [VocalicOyster](https://github.com/VocalicOyster), [questo]() invece è
il mio [Linkedin]() e questo il mio profilo [Fiverr]() nel caso in cui vi sia piaciuto come lavoro e 
vorreste commissionarmi qualche altro lavoro

[![](https://img.shields.io/badge/linkedin-blue?logo=linkedin)](https://www.linkedin.com/in/giovanni-innaimi/) 
[![](https://img.shields.io/badge/Fiverr-green?logo=fiverr&labelColor=%23004F1B)](https://it.fiverr.com/giovanniinnaimi)
[![](https://img.shields.io/badge/Instagram-%23E3314C?logo=instagram&logoColor=white)](https://www.instagram.com/foreachsolutions/)

