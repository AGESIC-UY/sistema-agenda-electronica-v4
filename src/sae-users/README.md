# OBJETIVO
Proyecto para la creación de usuarios administradores SAE

### Instalar dependencias locales

Entrar en saev4\src\sae-users\libs y ejecutar el siguiente comando:

mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile=SS-GENERICDAO-JPA2-8.8.0.jar -DgroupId=sofis.genericdao.jpa2 -DartifactId=SS-GENERICDAO-JPA2 -Dversion=8.8.0 -Dpackaging=jar -DgeneratePom

### Ejecutar Maven install

Se debe ejecutar un maven install sobre el pom raíz para que instale todos los proyectos y sus dependencias.