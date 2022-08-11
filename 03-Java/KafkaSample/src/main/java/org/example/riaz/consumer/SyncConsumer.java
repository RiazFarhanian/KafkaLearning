package org.example.riaz.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class SyncConsumer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.DoubleDeserializer");
        properties.setProperty("group.id", "PriceGroup");


        try (KafkaConsumer<String, Double> kafkaConsumer = new KafkaConsumer<>(properties)) {
            kafkaConsumer.subscribe(Collections.singletonList("price"));
            ConsumerRecords<String, Double> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(30));
            consumerRecords.forEach(record -> System.out.println("Record.key = " + record.key() + ", Record.value = " + record.value()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
