package org.example.riaz.kafka.lesson4.consumer;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.example.riaz.kafka.lesson3.Order;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class OrderConsumer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.deserializer", KafkaAvroDeserializer.class.getName());
        properties.setProperty("value.deserializer", KafkaAvroDeserializer.class.getName());
        properties.setProperty("group.id", "OrderGroup");
//        properties.setProperty("specific.avro.reader", "true");//Activate Avro to use Specific Type Serializer not Generic Type
        properties.setProperty("schema.registry.url", "http://localhost:8081");


        try (KafkaConsumer<String, GenericRecord> kafkaConsumer = new KafkaConsumer<>(properties)) {
            kafkaConsumer.subscribe(Collections.singletonList("OrderAvroGRTopic"));
            ConsumerRecords<String, GenericRecord> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(30));
            consumerRecords.forEach(record -> System.out.println("Record.key = " + record.key() + ", Record.value = " + record.value().toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
