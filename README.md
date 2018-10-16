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

When making schema changes, you can change value for "hibernate.hbm2ddl.auto" to "create".
However, after doing so, the user_roles table must be seeded with the values:

 1 - "ROLE_USER"
 
 2 - "ROLE_ADMIN"

In that order.  This is coupled with the Roles enum.  This is probably a code smell but I didn't want to
check if those values already existed with the context start up and then insert them if not.  And then
constantly fetch that data based on magic strings of "user" or "admin".  So I figured this enum was the
lesser of the two evils, idk.

Also, the most interesting part of the app is the user management page obviously, but you can't navigate to
those pages unless your current user has admin privileges.  So the first admin has to be created with SQL,
and then that admin can promote other users to admin.


