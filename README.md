# AWS Demo

The purpose of this project is to allow users to login and sign up.

## Creating the database

This project uses a PostgreSQL database. In the create_db_instructions.txt file there are 
instructions for setting up a PostgreSQL DB using Docker.

After the database is configured, create the database schema (run create_db.sql file) .

Database proprieties (URL, username, password) are set as configuration variables. 
In the terminal window where the app will be executed, run the following (replace with your own username/password):

```
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/awsdemo_db
export SPRING_DATASOURCE_USERNAME=awsdemo_db_user
export SPRING_DATASOURCE_PASSWORD=abc123def
export SPRING_JPA_HIBERNATE_DDL_AUTO=none
```

## Running the project

### Build, install

```
mvn clean install
java -jar target/awsdemo-0.0.1-SNAPSHOT.jar
```

### Sign up

Execute the following POST request:
```
POST localhost:8080/users/signup
{
    "userName": "test1",
    "password": "test"
}
```

### Login

Execute the following POST request:

```
POST localhost:8080/oauth/token

Header:
Authorization: Basic Auth (username: oauth.jwt.client-id, password: oauth.jwt.client-secret from application.properties)

Body: the followig params, as x-www-form-urlencoded
username test1
password test
grant_type password
```

Response (example) :
```
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidXNlcnMiXSwidXNlcl9uYW1lIjoidGVzdDEiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNjQ3MTM4NjcwLCJhdXRob3JpdGllcyI6WyJ1c2VyIl0sImp0aSI6IjVjZGQ2M2EwLTY1ZGItNGFmMi04YmQ4LTA0MmMxY2Y2NGYwMSIsImNsaWVudF9pZCI6ImF3c2RlbW8ifQ.dP7Lcz49p_4iY6OsklXNed1txK5rECWYub6fhilPA1A",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidXNlcnMiXSwidXNlcl9uYW1lIjoidGVzdDEiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiYXRpIjoiNWNkZDYzYTAtNjVkYi00YWYyLThiZDgtMDQyYzFjZjY0ZjAxIiwiZXhwIjoxNjQ5Njg3NDcwLCJhdXRob3JpdGllcyI6WyJ1c2VyIl0sImp0aSI6ImI2MGFhZGM0LTFkNTMtNDc4Ny05YTNmLTU4MmQxYWIwMzE0NiIsImNsaWVudF9pZCI6ImF3c2RlbW8ifQ.nMC1uiMn3X8xGQhnHTU9tG-W5rfpGoSXSb23x1-BgNQ",
    "expires_in": 43199,
    "scope": "read write",
    "jti": "5cdd63a0-65db-4af2-8bd8-042c1cf64f01"
}
```

## Test that login was successful

Execute the following request:
```
GET localhost:8080/users/test

Header:
Authorization: Bearer <access_token fom above example>
```

[Here](https://bogdanstirbat.github.io/jekyll/update/2022/03/13/spring-boot-aws-beanstalk.html) there are instructions
on deploying the app to AWS.