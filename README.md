Developer Task 1 - Debug JAVA Application

Task Objective
The objective of this task is to test the capability of the candidate at:

•	Troubleshooting issues with existing code bases.
•	Maven multi-module structure
•	Knowledge of Java SE
•	Java Web services (SOAP)
•	Logging using SLF4J and LOG4J
•	Java Persistence API
•	Spring appreciation
•	Spring Restful API
•	Test Driven Development

This application has been deliberately bugged in various ways. Your task is to find each bug or logic problem
and fix it.

Pre-requisites
1.	You need Java JDK 1.8
2.	Have maven installed on your machine (Maven 3 +)
3.	Any IDE of your choice (preferably any one of these : Intelij, Eclipse, RAD, NetBeans or “Notepad++” )

DOs and Don’ts
•	Never change the maven settings unless you are a maven guru
•	Never edit any existing test suites, make them pass by fixing bugs in other parts of this code base
•	Never change the IntelligentNetworkService.wsdl file in resources folder of module electronic-payments-business in any way
•	This is a self contained application, no external containers or databases required, soap web service is being published using java SE application and there is no need for an Application Server or Servlet Container

Hints
•	Module intelligent-network-api (a java SE application) is the one providing the SOAP web service and it’s being consumed by electronic-payments-business , you need to make sure it’s running properly when testing your application

Task Requirements
•	You will identify each issue and log it in your own manner (textbook, notepad, bug issue tracking system)
•	You will commit individual bug fixes to any Source Code Manager of your choice.
•	You will comment and describe each commit meticulously
•	At the end you are required to execute the mvn clean verify command in the root folder - without getting any errors
•	Write Unit Test for the code in module electronic-payments-business, and make sure code coverage reaches 70% + in this module
•	After this run the embedded jetty in module electronic-payments-api
•	Bonus points to be given to those who make the intelligent-network-api module Web Service connection-pooling enabled.

