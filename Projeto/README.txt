Follow the following steps to run the application:


#CREATE A PRIVATE DOCKER NETWORK
docker network create moodprism-network

#RUN KAFKA NETWORK-MOODPRISM-----172.18.0.2
docker run --rm -it --network moodprism-network -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=172.18.0.2 --env ADVERTISED_PORT=9092 --name kafka spotify/kafka

#CREATE TOPIC
docker exec kafka /opt/kafka_2.11-0.10.1.0/bin/kafka-topics.sh --create --zookeeper 172.18.0.2:2181 --replication-factor 1 --partitions 1 --topic moodprismTopic

#BUILD AND START MOODPRISM (inside moodprism folder)
docker build -t moodprism .
docker run --rm -it --network moodprism-network -p 8080:8080 --name moodprism moodprism

#TEST THE APPLICATION
http://localhost:8080/