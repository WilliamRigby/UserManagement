To run this application locally, use this command:

java -jar target/dependency/webapp-runner.jar target/*.war

OR, if you want to debug locally, then create an IntelliJ Maven configuration:

tomcat7: run

This will launch a local web server, using a maven plugin tomcat7-maven-plugin.
This will allow you to set breakpoints and debug locally.

When running locally, you must create these environment variables on your dev machine:

JDBC_DATABASE_URL : jdbc:postgresql://{server}:{port}/{db}?user={user}&password={password}&sslmode=require
JDBC_DATABASE_USERNAME : {username}
JDBC_DATABASE_PASSWORD : {password}

When deploying to prod, these vars will already exist for the prod database


