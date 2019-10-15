# Mutant
Detector de Mutantes:
Aplicaion api rest donde recibe como parámetro, una secuencia del ADN (A,T,C,G), las
cuales representa cada base nitrogenada. 

Es considerado mutante si hay una secuencia de 4 letras repetidas en el ADN. 

### Prerequisitos
- Cuenta en Amanzon Webservices
- Usuario aws configurado con permisos de acceso.
- Base de datos DynamoDB. 

### Requerimientos de Software

Programas               | Version
:-----------------------|:----------
 Spring Boot            |2.0.4.
 Java   	               |1.8
 Maven                  |3.6.1
 DynamoDB               |1.11.408
 JUnit                  |4.12


### Herramientas:
Programas                | Descripción
:-----------------------|:----------
 Insomnia               |Cliente Rest
 Amazon Webservices     |Cloud Amazon para subir la app Rest
 JUnit 					            |Test Unitarios
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

### Ejecución en entorno remoto AWS:

Actualmente se encuentra instalada en Amazon Web Services  el detector mutante:

Adjuntos URL:

URL                | Descripción    | Metodo
:-----------------------|:----------|:-----------
http://mutant-0805.sa-east-1.elasticbeanstalk.com/status |Verifica si la el api esta funcionando | GET
http://mutant-0805.sa-east-1.elasticbeanstalk.com/mutant  |Verifica el ADN y detecta si es mutante | POST
http://mutant-0805.sa-east-1.elasticbeanstalk.com/stats  |Muestra las estadisticas de mutantes y humanos| GET



***Detentando mutantes***

Para detectar mutantes es necesario enviar al servicio /mutan un json con las siguientes caracteristicas:
```bash
{
 "dna":["AAATGC","CCGGAC","TTACAT","AGAAGG","CCCCTA","TCACTG"]
}
```
***Consultando Estadisticas***
Para ver el conteo de humanos y mutantes consultar el siguiente servicio stats

http://mutant-0805.sa-east-1.elasticbeanstalk.com/stats


### Test Unitarios:


### Coverage:



### Pruebas Jmetter: 

