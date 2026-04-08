# SAE - VERSION 4.6.6

## OBJETO
Proyecto SAE 

### Instalar dependencias locales

Entrar en `saev4\src\sae-modulos\libs` y ejecutar el siguiente comando:

`mvn install:install-file -Dfile=immOpenCSVExt.jar -DgroupId=uy.gub.imm -DartifactId=opencsv.ext -Dversion=1.0 -Dpackaging=jar`

### Ejecutar Maven install

Se debe ejecutar un `mvn install` sobre el pom raíz para que instale todos los proyectos y sus dependencias.
Se recomienda crear la variable de entorno `SAE4_DEPLOYMENT_FOLDER` apunta al directorio deployment de Wildfly, por ejemplo `D:\Servers\wildfly-24.0.1.Final\standalone\deployments` para copiar automáticamente el .ear generado luego de la compilación. Ver detalles en `sae-ear/pom.xml`

### Ejecutar análisis de sonarqube
Situarse en los subproyectos y correr la siguiente sentencia:

`mvn sonar:sonar -Dsonar.host.url=SonarQube -Dsonar.login=<token en sonarqube>`

### Host 
Se requiere configurar un host local para utilizar la aplicación, abrir el archivo `C:\Windows\System32\Drivers\etc\hosts`

Agregar lo siguiente:

`127.0.0.1 testing.sae.pge.red.uy`

## Compilación para ID Uruguay (activa por defecto)
mvn clean install -P auth-iduruguay

## Compilación para local
mvn clean install -P auth-local


