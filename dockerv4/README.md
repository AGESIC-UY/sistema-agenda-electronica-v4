# OBJETO
El presente proyecto permite levantar mediante docker compose la aplicacion SAE, para ello se levanta un contenedor con wildfly y otro con postgres 
inicializando el mismo (la primera vez) con una carga inicial de SAE.

De esta forma se brinda un armado agil de la estructura necesaria para los equipos de desarrollo. Y se comienza a recorrer el camino de "containerizar" a SAE
y separarlo en componentes con responsabilidades bien definidas. 

# Instructivo
## SSL por defecto en Wildfly
Es necesario crear la carpeta postgres dentro de volumes ya que la misma no se sube a git para no subir los datos de la bd local.

## SSL por defecto en Wildfly
Por defecto, los puertos usados por Wildfly para atender conexiones HTTPS es el 8443

## Desplegar Aplicacion
### Alternativa 1: Habilitar Consola Management en Wildfly para subir ears cerrados
Es necesario conectar por unica vez al contenedor docker y ejecutar lo siguiente estando parado en /opt/wildfly/bin

./add-user.sh -u 'admin' -p 'admin'

Luego podemos deployear el ear accediendo a http://localhost:9990

### Alternativa 2: Utilizar carpeta deployments para subir los ear
Se creo un volumen en ./volumes/wildfly24/deployments se puede colocar en esta carpeta los ear que queremos que se desplieguen y crear un archivo con el nombre del ear y extension .dodeploy.

## Actualizacion de scripts para inicializacion de la BD

Por defecto, se configuro para ejecutar una carga inicial al crear la base de datos sae en Postgres.

En caso de que en el desarrollo se modifique la estructura de la BD es necesario traer los cambios al proyecto de docker-compose. Para
ello copiar los archivos que se encuentran en el codigo fuente de la aplicacion:

* saev4\src\sae-ear\src\main\application\esquema_global.sql
* saev4\src\sae-ear\src\main\application\esquema_template.sql
* saev4\src\sae-ear\src\main\configuraciones_nuevo_organismo.sql

Y copiarlos, sobreescribiendo los existentes, en volumes/sae-sql. Donde, es necesario renombrarlos:

* esquema_template.sql = esquema_sae_development.sql
* configuraciones_nuevo_organismo.sql = configuraciones_sae_development.sql

Los scripts se ejecutan automaticamente la primera vez en orden alfabetico, por lo que es importante mantener la numeracion:
01-esquema_global.sql
02-esquema_sae_development.sql
03-configuraciones_sae_develpment.sql

### Modificaciones: Dentro de los mismos cambiar:
* Debido a que los scripts estan pensados para ejecutar dentro de la bd sae creando esquemas, hay que agregarles una primera linea con el siguiente contenido: \c sae
* esquema_template: Reemplazar {esquema} con sae_development
* configuraciones_nuevo_organismo: <id>,<codigo>,<nombre> por 1,'1','sae_development'

### Consideraciones adicionales:
- Alternativa manual que ejecuta automaticamente los scripts:
su postgres
psql --host localhost --port 5432 
\i /docker-entrypoint-initdb.d/01-esquema_global.sql
\i /docker-entrypoint-initdb.d/02-esquema_sae_development.sql
\i /docker-entrypoint-initdb.d/03-configuraciones_sae_develpment.sql

### LOGS

No se modifico el comportamiento de los logs ni configuro syslog.

# Configuraciones adicionales

Aqui se detallan secciones del documento que es necesario configurar en ambiente de desarrollo y no forman parte del docker.

## Particulares de las integraciones
Ver documentacion.

## Correciones necesarias en la documentacion

- Toda la seccion de docker en entorno desarrollo cambiara a referenciar este proyecto de docker compose.

## Levantar DB en formato sql

C:\"Program Files"\PostgreSQL\10\bin\psql.exe -h localhost -p 5433 -U sae -d sae-vm -f test-db-sae.sql