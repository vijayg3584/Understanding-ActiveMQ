# Understanding Apache ActiveMQ
This project implements both RESTful and SOAP web services. The project is developed as a Spring Boot application using Spring MVC for exposing the web services. It used Spring JMS for interacting with the JMS queue and Spring Data for persistence.

Apache ActiveMQ is used as the JMS queue. The app is configured to connect to ActiveMQ at the default setup. The url for which is http://localhost:8161/admin/browse.jsp?JMSDestination=number-queue. "number-queue" is the name of the queue.

Apache Derby in-memory database is used for persistence. This can be easily changed to MySQL, Oracle or any SQL database.

### RESTful Web Services
Two RESTful web services are exposed at endpoint `/restapi/numbers`.
The REST service is secured by Http Basic Authentication. The username is unico-user1 and password is a1a2a3a4.
1. 'push' - Takes two numbers as parameters and inserts them into the queue as well as database. This uses http post method.
2. 'list' - Gets all the numbers stored in the database. This uses http get method. No rarameters reqired.

### SOAP Web Services
Three SOAP web services are exposed at the endpoint `/gcd`. WS Security with Username and Timestamp. Username is unico-soap-user and password is a1a2a3a4.
1. 'gcd' - Gets the gcd of the top two numbers in the queue and removes them from the queue. This has no effect on the database.
2. 'gcdList' - Gets the list of all computed gcd from the database.
3. 'gcdSum' - Gets the sum of all the computed gcd from the database.

This application is tested as standalone spring boot application as well being deployed to Tomcat application server.
