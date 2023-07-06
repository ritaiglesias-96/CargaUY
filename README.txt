![License](https://img.shields.io/github/license//CargaUY.svg?style=for-the-badge) ![Repo Size](https://img.shields.io/github/languages/code-size//CargaUY.svg?style=for-the-badge) ![TOP_LANGUAGE](https://img.shields.io/github/languages/top//CargaUY.svg?style=for-the-badge) ![FORKS](https://img.shields.io/github/forks//CargaUY.svg?style=for-the-badge&social) ![Stars](https://img.shields.io/github/stars//CargaUY.svg?style=for-the-badge)
# CargaUY

## Table of Contents

- [Description](#description)
- [Screenshots](#screenshots)
- [Built With](#built-with)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [License](#license)
- [Acknowledgements](#acknowledgements)
- [Contacts](#contacts)

## Description

Todas las actividades económicas dependen de cadenas de distribución: tanto en la llegada de materias primas a centros industriales como en la distribución
de todo tipo de productos para llegar a los consumidores finales. En Uruguay
a nivel interno la mayoría del transporte de dichas cadenas de distribución se
realiza a través del transporte carretero. 

CargaUY es una aplicación que brinda información sobre la actividad del transporte de carga para objetivos de recaudación, la fiscalización de la actividad y la planificación de la infraestructura necesaria a futuro. 

## Screenshots

<img src="" />## Built With

<a href="https://docs.oracle.com/javase/tutorial/index.html"><img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" height="40px" width="40px" /></a><a href="https://www.postgresql.org/"><img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/postgresql/postgresql-original.svg" height="40px" width="40px" /></a>

## Getting Started



### Prerequisites

Intellij 
PostgreSQL
WIldfly 24

### Installation

This project was created from the archetype "wildfly-jakartaee-ear-archetype".

To deploy it:
Run the maven goals "install wildfly:deploy"

To undeploy it:
Run the maven goals "wildfly:undeploy"

## Usage

DataSource:
This sample includes a "persistence.xml" file in the EJB project. This file defines
a persistence unit "CargaUyPersistenceUnit" which uses the JakartaEE default database.

In production environment, you should define a database in WildFly config and point to this database
in "persistence.xml".

If you don't use entity beans, you can delete "persistence.xml".

JSF:
The web application is prepared for JSF 2.3 by bundling an empty "faces-config.xml" in "src/main/webapp/WEB-INF".
In case you don't want to use JSF, simply delete this file and "src/main/webapp/beans.xml" and "src/main/java/tse/java/Jsf23Activator.java"

Testing:
This sample is prepared for running unit tests with the Arquillian framework.

The configuration can be found in "CargaUy/pom.xml":

Three profiles are defined:
-"default": no integration tests are executed.
-"arq-remote": you have to start a WildFly server on your machine. The tests are executed by deploying 
 the application to this server.
 Here the "maven-failsafe-plugin" is enabled so that integration tests can be run.
 Run maven with these arguments: "clean verify -Parq-remote"
-"arq-managed": this requires the environment variable "JBOSS_HOME" to be set: 
 The server found in this path is started and the tests are executed by deploying the application to this server.
 Instead of using this environment variable, you can also define the path in "arquillian.xml".
 Here the "maven-failsafe-plugin" is enabled so that integration tests can be run.
 Run maven with these arguments: "clean verify -Parq-managed"

The Arquillian test runner is configured with the file "src/test/resources/arquillian.xml" 
(duplicated in EJB and WEB project, depending where your tests are placed).
The profile "arq-remote" uses the container qualifier "remote" in this file.
The profile "arq-managed" uses the container qualifier "managed" in this file.


Unit tests can be added to EJB project and/or to Web project.

The web project contains an integration test "SampleIT" which shows how to create the deployable EAR file using the ShrinkWrap API.
You can delete this test file if no tests are necessary.

Why integration tests instead of the "maven-surefire-plugin" testrunner?
The Arquillian test runner deploys the EAR file to the WildFly server and thus you have to build it yourself with the ShrinkWrap API.
The goal "verify" (which triggers the maven-surefire-plugin) is executed later in the maven build lifecyle than the "test" goal so that the target 
artifacts ("CargaUy-ejb.jar" and "CargaUy-web.war") are already built. You can build
the final EAR by including those files. The "maven-surefire-plugin" is executed before the JAR/WAR files
are created, so those JAR/WAR files would have to be built in the "@Deployment" method, too. 


## License

<a href="https://choosealicense.com/licenses/apache-2.0/"><img src="https://raw.githubusercontent.com/johnturner4004/readme-generator/master/src/components/assets/images/apache.svg" height=40 />Apache License 2.0</a>

## Acknowledgements

Adrian Gioda, Diego Bronc, Marcelo 

## Contacts

<a href="https://www.linkedin.com/in/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" /></a>  <a href="mailto:"><img src=https://raw.githubusercontent.com/johnturner4004/readme-generator/master/src/components/assets/images/email_me_button_icon_151852.svg /></a>
