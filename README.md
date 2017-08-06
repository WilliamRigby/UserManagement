To run this application locally, use this command:

java -jar target/dependency/webapp-runner.jar target/*.war

OR, if you want to debug locally, then create an IntelliJ Maven configuration:

tomcat7: run

This will launch a local web server, using a maven plugin tomcat7-maven-plugin.
This will allow you to set breakpoints and debug locally.


When running locally, comment out these lines in MvcConfiguration.java:

String JDBC_DATABASE_URL = System.getenv("JDBC_DATABASE_URL");
String JDBC_DATABASE_USERNAME = System.getenv("JDBC_DATABASE_USERNAME");
String JDBC_DATABASE_PASSWORD = System.getenv("JDBC_DATABASE_PASSWORD");

However, everytime you push to the heroku repo, make sure you uncomment them, because this is how we 
set the DB configuration variables.  The way I get them for running locally is a bit hacky, but it
works.

