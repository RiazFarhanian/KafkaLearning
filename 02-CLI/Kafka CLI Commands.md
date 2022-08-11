# [![N|Solid](https://upload.wikimedia.org/wikipedia/commons/thumb/0/0a/Apache_kafka-icon.svg/128px-Apache_kafka-icon.svg.png?20181125133418)](https://kafka.apache.org) Kafka CLI COMMANDS 
## _Setup_:
### For Windows:

```sh
zookeeper-server-start <KAFKA_DIR>\zookeeper.properties
kafka-server-start <KAFKA_DIR>\server.properties
```

### For Linux:

```sh
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
```

## _Commands_:

```sh
kafka-topics --list --bootstrap-server localhost:9092

kafka-console-consumer --bootstrap-server localhost:9092 --topic first-topic

kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic first-topic

kafka-topics --describe --bootstrap-server localhost:9092 --topic first-topic

kafka-console-producer --broker-list localhost:9092 --topic first-topic
```
