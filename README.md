
[![Build Status](https://travis-ci.com/lukaszrys/movie-rater.svg?token=WxMp5FXszxp4REupefyE&branch=develop)](https://travis-ci.com/lukaszrys/movie-rater)
# movie-criticts
Rating application for movies

# Docker deployment

### Requirements

* Docker
* Docker-compose
* Java 11 (with JAVA_HOME pointing at it)

### Running
From project root run `./deploy_docker.sh` (or execute manually commands inside).
If no changes to application are made after running above script, the `docker-compose -f ./docker/docker-compose.yml up -d` will be enough to restart environment.

This will start:

* MongoDB (with populated data)
* full rate-service (backend + frontend). It will use `application-docker.yml` configuration file.

The application is now fully accessible on `http://localhost:8080`.

# Local deployment

### Requirements:

* Java 11 (with JAVA_HOME pointing at it)
* MongoDB / Docker-compose
* Node.js >= 8.11

### Running

1. Go to project root
2. Set up MongoDB (`docker-compose -f ./docker/docker-compose.yml up -d mongo mongo-seed` or locally)
3. Run `./mvnw -f ./rate-service/pom.xml spring-boot:run`. This will start rate-service application on port 8080
4. Run `npm --prefix ./rate-service-gui start`. This will start rate-service-gui application on port 3000