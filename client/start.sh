#!/bin/bash
mvn clean package
sudo docker build -t image/client-container .
sudo docker run -p 6666:6666/udp -i -t --name client-container --link server-container:server-container image/client-container
