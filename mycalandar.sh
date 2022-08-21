#!/bin/sh
set -e

docker-compose up -d
cd calendarmanager
java -jar target/calendarmanager-0.0.1-SNAPSHOT.jar
