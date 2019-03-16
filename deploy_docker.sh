#!/bin/bash

./mvnw clean install -DskipTests
docker-compose -f ./docker/docker-compose.yml build
docker-compose -f ./docker/docker-compose.yml down
docker-compose -f ./docker/docker-compose.yml up -d