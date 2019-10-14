# Mutant
Detector de Mutantes

### Prerequisitos
- Cuenta en Amanzon Webservices
- Usuario aws configurado con permisos de acceso.
- Base de datos DynamoDB. 

### Requerimientos de Software

Programas               | Version
:-----------------------|:----------
 Spring Boot            |2.0.4.
 Java   	              |1.8
 Maven                  |3.6.1
 DynamoDB               |1.11.408
 JUnit                  |4.12


### Herramientas:
Programas                | Descripción
:-----------------------|:----------
 Insomnia               |Cliente Rest
 Amazon Webservices     |Cloud Amazon para subir la app Rest
 JUnit 					        |Test Unitarios
 JMeter                 |Realizar pruebas de stress

### Entorno AWS DYNAMO:

***Crear Base de datos NoSQL:*** 

- Entrar a la cuenta de AWS. 
- Ir a la seccion de servicios->DynamoDB
- luego, seleccionar la opcion de crear tabla.
 
Es necesario que la tabla se llame: human con Clave de partición principal como id.	


### Ejecución en entorno local
 
***Clonar repositorio:*** 
```bash
git clone https://github.com/jesusrafael9/mutant.git
```

Renombrar el archivo: application.yml.default con el siguiente nombre application.yml

Editar el archivo con los parametros a conexion DynamoDB AWS

```bash
amazon:
  access:
    key: <Acces-Key-amazon>
    secret-key: <Secret-key-amazon>
  region:<Region>
```
Luego ejecutar la aplicación: 
```bash
mvn spring-boot:run
```
