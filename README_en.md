# HR Management for Businesses

<a href="https://www.instagram.com/foreachsolutions/">
<img src="/images/ForeachSolutions.jpg" width="150" height="150" style="border-radius: 2px;" alt="a">
</a>

# Index

- [Brief introduction](#introduction)
- [Documentation](#documentation)
- [Detailed explanation](#detailed-explanation)
- [Technology stack](#technology-stack)
- [Community](#community)
- [How to contribute](#how-to-contribute)
- [Road map](#road-map)

[LEGGI IN ITALIANO](README.md)

# Introduction

#### Human resource management mainly aimed at companies

The application was created for the purpose of being used by companies, for facilitated management
of human resources, (so employees, candidates with related applications, etc...).
We find the possibility to manage their employees by adding a hiring of the
related employee, adding for each employee leave, sick days,
vacation days, overtime, upload payroll to facilitate management along with the administrative part
of the company,
managing applications with related candidates, applications and scheduling days and times of
interviews

![general project structure](/images/project%20structure.png)

![structure](/images/structure.png)

![controller](/images/controller.png)

# How to start

All that is needed for the application to run completely is:

- Have installed a JDK (ver. 21 or higher).
- Have installed Sql (Recommended: XAMPP) with a database renamed "human_resources"


#### Currently running at localhost:8080

[Java installation](https://www.java.com/it/download/manual.jsp)

[XAMPP installation](https://www.apachefriends.org/it/index.html)

[Return to index](#index)

# Documentation

For complete documentation, start the application and go to the
[Swagger](http://localhost:8080/swagger-ui/index.html) page
already present in the project itself.
All the endpoints will be shown, and you will also be able to try them out. You can find
more info on SpringDoc [here](https://springdoc.org/)

![Documentation](/images/documentazione.png)

[Return to index](#index)

# Detailed explanation

As mentioned in the introduction, this application is aimed at companies,
for the management of their human resources. Mainly it deals with managing:

- Employees and related: hiring, hiring department, leave, overtime, sick days and days of
  vacations
- Job ads with related: candidates and related evaluation, interviews by departments

#### Note the fact that it will be possible to register multiple companies

The entities used are:

- Applicants: represent candidates for a job announcement
- CandidateEvaluations: represents the evaluation of applicants
- Department: represents a department of the company
- Employees: needless to explain
- Hiring: represents the details of hiring an employee
- Interview: represents the (future) interview of a candidate
- Job announcement: needless to explain
- Overtime, Permits, SickDays and Vacation: overtime, leave, sick and vacation days of an employee
- User: represents the user company

There is also a seeding function, which allows the initial filling of the database with preset values,
thus having the possibility to run initial tests

[Return to index](#index)

# Technology stack

Various technologies were used in the development of this application. We have:


<img src="https://logowik.com/content/uploads/images/java1655.logowik.com.webp" width="48" height="48" style="border-radius: 10px" alt="JAVA"/>  <img src="/images/icons8-spring-boot-48.png" alt="SpringBoot"/>  <img src="images/springsecurity.png" width="48" height="48" style="border-radius: 10px"/>  <img src="https://upload.wikimedia.org/wikipedia/commons/8/87/Sql_data_base_with_logo.png" height="48" style="border-radius: 10px"/><img src="images/springsecurity.png" width="48" height="48" style="border-radius: 10px"/>  <img src="https://seeklogo.com/images/J/json-web-tokens-jwt-io-logo-C003DEC47A-seeklogo.com.png" width="48" height="48" style="border-radius: 10px"/>  <img src="images/tomcat.png"width="48" height="48" alt="TOMCAT"/><img src="https://miro.medium.com/v2/resize:fit:400/0*jba3dz1j64rfhl5i.jpg" width="48" height="48" style="border-radius: 10px"  alt="Hibernate" />  <img src="https://help.apiary.io/images/swagger-logo.png" width="48" height="48" alt="SWAGGER"/>  <img src="images/Apache_Feather_Logo.png" width="48" height="48" style="border-radius: 10px"/>  <img src="/images/tomcat.png" width="48" height="48"/>

[Return to index](#index)

# Community

For any improvement ideas for the project, write on gitter to "#hr@VocalicOyster:gitter.im"

[Return to index](#index)

# How to contribute

For any kind of contribution, create a branch (source branch: DEV) with the format "feat/nameFeature"
and create the PR

[Return to index](#index)

# Road Map

These are the features I would like to implement next. This here is only a first version
and, of course, bugs and glitches may be present

- Addition of management (downloading, uploading and deleting) of candidates' CVs

Other future ideas will be included here

[Return to index](#index)

# AUTHOR

[![](https://img.shields.io/badge/linkedin-blue?logo=linkedin)](https://www.linkedin.com/in/giovanni-innaimi/)
[![](https://img.shields.io/badge/Fiverr-green?logo=fiverr&labelColor=%23004F1B)](https://it.fiverr.com/giovanniinnaimi)
[![](https://img.shields.io/badge/Instagram-%23E3314C?logo=instagram&logoColor=white)](https://www.instagram.com/foreachsolutions/)

[Return to index](#index)


