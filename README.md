# INSTRUCTIONS TO RUN THE PROJECT

# Prerequisites:
- Java Development Kit (JDK) 17
- Maven
- PostgreSQL 16
- Git

# Setting Up PostgreSQL:
- Install PostgreSQL
- Follow the instructions on the official PostgreSQL website to install PostgreSQL.
- Create a Database and User:
- Open the PostgreSQL command line tool (psql) and run the following commands to create a database and a user:


CREATE DATABASE bgs;
CREATE USER postgres WITH ENCRYPTED PASSWORD 'postgres';
GRANT ALL PRIVILEGES ON DATABASE bgs TO postgres;


Run the SQL Script:
The repository includes an SQL script (bgs-task.sql) to set up the database schema and initial data. Execute the script using the following command:

psql -U postgres -d bgs -f path/to/bgs-task.sql

Replace path/to/bgs-task.sql with the actual path to the bgs-task.sql file in your cloned repository.


# Setting Up the Application:
- Clone the Repository using Git

git clone https://github.com/ferodrigop/back_end_developer_task.git

cd repository-location

# Build the Application:

Use Maven to build the application:
- while on the path where pom.xml file is located run:
mvn clean install

Run the Application:
- move to target folder and run:
java -jar backend-task-0.0.1-SNAPSHOT.jar


# API Documentation:

https://documenter.getpostman.com/view/25497197/2sA3QtdWXg#e4760ccc-71e8-4a83-bc47-1a780cd20d5f
