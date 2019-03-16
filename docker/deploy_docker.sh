#!/bin/bash

../mvnw -f ../pom.xml clean build
docker-compose build
docker-compose down
docker-compose up -d