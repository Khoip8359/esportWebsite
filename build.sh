#!/bin/bash

# Install Java 17
apt-get update
apt-get install -y openjdk-17-jdk

# Make mvnw executable
chmod +x mvnw

# Build the application
./mvnw clean package -DskipTests

# Run the application
java -jar target/Esport_Website-0.0.1-SNAPSHOT.jar 