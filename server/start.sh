#!/bin/bash
mvn clean package
sudo docker build -t image/server-container .
sudo docker run -p 6677:6677/udp -d --name server-container image/server-container
