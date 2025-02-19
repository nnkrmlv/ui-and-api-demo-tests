# ui-and-api-demo-tests
Automated testing project for UI and API tests using Selenium, RestAssured, JUnit and Docker.


## Project Overview
This project is designed to automate UI and API testing using:

- UI Tests: Selenium with JUnit5 
- API Tests: RestAssured
- Programming Language: Java 21
- Build Tool and Test execution: Maven
- Test Environment: Docker
- Supported Browsers: Chrome, Firefox


## Prerequisites:
- Java 21 installed
- Maven 3.8+ installed
- Docker and Docker Compose installed


# Set up and Run project:

- clone repo to your local machine: 
  - `git clone git@github.com:nnkrmlv/ui-and-api-demo-tests.git`
  - `cd ui-and-api-demo-tests`
- start Selenium Grid with Docker Compose:
  - `docker compose up -d`
  - check Selenium Grid Status here: http://localhost:4444/ui/ to verify if the nodes are ready
- run API tests:
  - `mvn test -P api-tests`
- run UI tests in Firefox 
  - `mvn test -Dui.test.browser=firefox -P ui-tests`
- run UI tests in Chrome
  - `mvn test -Dui.test.browser=chrome -P ui-tests`
- stop Docker Containers
  - `docker compose down`
