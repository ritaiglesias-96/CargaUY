# Especifica la imagen base
FROM tomcat:latest

RUN mkdir /usr/local/tomcat/json

COPY ../src/main/resources/empresas.json /usr/local/tomcat/json/

COPY ../src/main/resources/ciudadanos.json /usr/local/tomcat/json/

# Copia el archivo WAR a la carpeta del contenedor renombrandolo
COPY target/ProveedorEmpresas-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/pdi.war

# Comando a ejecutar cuando se inicie el contenedor
CMD ["catalina.sh", "run"]