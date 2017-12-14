<h1> HappyPatients Hospital Redesign </h1>

This repository contains all the files required to change the HappyPatients hospital from a monolith to an easy to understand and operate microservices architecture. <br>

The different components used in this project include <br>

1. RESTful web service <br>
2. Cassandra database <br>
3. Hazelcast cache <br>
4. Messaging with Active MQ <br>
5. Apache Camel <br>
6. Java SPRING <br>

The redesign of the HappyPatients hospital has the following architecture: <br>

![alt text](https://github.com/svishrut93/Distributed-computing/blob/master/Happy%20Hospital%20Project/Architecture/architecture.PNG)


Some things to note before running the application is that the cassandra version used for this project is 2.2 and using the 3.x release might lead to unexpected behavior.<br> 


Broadly the project is organised into two folders.<br> 
1. DC Project<br> 
2. PolicyServer<br> 

The main folder containing all the java packages to run a RESTful webservice connected to the cassandra database can be found inside DC Project : src-main-java.zip.<br>
All dependencies needed to run the RESTful webservice are speecified inside POM.xml of DC Project folder.<br>
The RESTful webservice is built using SPRING and the database is connected using Spring-data-cassandra. 

To execute this part of the project run class PatientController.java as shown in the below diagram.<br>

![alt text](https://github.com/svishrut93/Distributed-computing/blob/master/Happy%20Hospital%20Project/Architecture/DC%20structure.PNG)

To execute the policy server run the file PolicyController.java inside PolicyServer-->src-->main-->java-->com-->java-->controller <br>


Both files from DC Project and Policy server must be run concurrently.<br> 
In order to execute the applications on the server , perform a ,maven build over the project. <br>
WAR files are not included in the repository because of their large size(>50MB), hence it is imperative to build project using maven. <br> 

Below is the schema used for cassandra database <br> 
keyspace name = hospital<br>
table name = patient<br>
![alt text](https://github.com/svishrut93/Distributed-computing/blob/master/Happy%20Hospital%20Project/Architecture/Schema.png)

The folder :ActiveMQConsumer contains all java files to perform 2 actions : Send Email and Analytics. 
Every time an action is performed on the database a message is sent to the 2 queues and corresponding actions are performed. 
The main class for this is called Application.java. 


There is also a PPT relating to the project that is attached for further reference - HappyPatientsHospitals.pptx

Feel free to contact me for any further clarifications.









