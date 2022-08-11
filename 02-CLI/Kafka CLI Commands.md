# Kafka CLI Commands
Setup:
For Windows:
zookeeper-server-start <KAFKA_DIR>\zookeeper.properties

kafka-server-start <KAFKA_DIR>\server.properties

For Linux:
bin/zookeeper-server-start.sh config/zookeeper.properties

bin/kafka-server-start.sh config/server.properties



————————————————————————————————


kafka-topics --list --bootstrap-server localhost:9092

kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic first-topic

kafka-topics --describe --bootstrap-server localhost:9092 --topic first-topic

kafka-console-consumer --bootstrap-server localhost:9092 --topic first-topic

kafka-console-producer --broker-list localhost:9092 --topic first-topic

