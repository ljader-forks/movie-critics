
[![Build Status](https://travis-ci.com/lukaszrys/movie-rater.svg?token=WxMp5FXszxp4REupefyE&branch=develop)](https://travis-ci.com/lukaszrys/movie-rater)
# movie-criticts
Rating application for movies

# Docker deployment

### Requirements

* Docker
* Docker-compose

### Running
From root project run `./deploy_docker.sh`.

This will start:

* MongoDB on port 27017
* rate-service application on port 8080

# Local deployment

### Requirements:

* Java 11
* MongoDB / Docker-compose
* Node.js >= 8.11

### Running

1. Go to project root
2. Set up MongoDB (`docker-compose -f ./docker/docker-compose.yml up -d mongo mongo-seed` or locally)
3. Run `./mvnw -f ./rate-service/pom.xml spring-boot:run`. This will start rate-service application on port 8080
4. Run `npm --prefix ./rate-service-gui start`. This will start rate-service-gui application on port 3000