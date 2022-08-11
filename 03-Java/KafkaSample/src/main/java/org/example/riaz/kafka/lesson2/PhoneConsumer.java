package org.example.riaz.kafka.lesson2;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class PhoneConsumer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.example.riaz.kafka.lesson2.deserializer.PhoneDeserializer");
        properties.setProperty("group.id", "PhoneGroup");


        try (KafkaConsumer<String, Phone> kafkaConsumer = new KafkaConsumer<>(properties)) {
            kafkaConsumer.subscribe(Collections.singletonList("phones"));
            ConsumerRecords<String, Phone> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(30));
            consumerRecords.forEach(record -> System.out.println("Record.key = " + record.key() + ", Record.value = " + record.value().toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
