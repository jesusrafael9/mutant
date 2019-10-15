# Mutant
Detector de Mutantes:
Es una aplicacion rest donde recibe como parámetro, una secuencia del ADN (A,T,C,G), la
cual representa cada base nitrogenada. 

Es considerado mutante si hay una secuencia de 4 letras repetidas en el ADN. 

### Pre-Requisitos
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



***Detectando mutantes***

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

![](https://user-images.githubusercontent.com/15696325/66794980-94023f80-eed9-11e9-97b0-1ac6e7a58ae8.png)

### Coverage:

![](https://user-images.githubusercontent.com/15696325/66794824-26eeaa00-eed9-11e9-8670-fa3514244739.png)

### Pruebas JMeter: 

Prueba 1000 usuarios:

![](https://user-images.githubusercontent.com/15696325/66797701-13940c80-eee2-11e9-90b8-a14edb8bc72f.png)

Reporte:

![](https://user-images.githubusercontent.com/15696325/66797873-91f0ae80-eee2-11e9-904e-185bc57fd9e7.png)


### Enlaces relacionados:

- http://jmeter.apache.org
- https://insomnia.rest/
- https://aws.amazon.com/es/elasticbeanstalk/
- https://aws.amazon.com/es/dynamodb/
