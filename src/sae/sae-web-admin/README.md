##EJECUTAR ANALISIS DE SONARQUBE

El comando es el siguiente:
mvn sonar:sonar -Dsonar.host.url=http://host:port -Dsonar.login=the-generated-token

Ejemplo:
mvn sonar:sonar -Dsonar.host.url=https://sonarqube.sisinfo.com.uy -Dsonar.login=80269692e769eddf54350a1b36369bd6dd8ff659