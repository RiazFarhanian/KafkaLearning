package org.example.riaz.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ASyncProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.DoubleSerializer");

        try (KafkaProducer<String, Double> kafkaProducer = new KafkaProducer<>(properties)) {
            ProducerRecord<String, Double> record = new ProducerRecord<>("price", "Iphone 13", 12000d);
            kafkaProducer.send(record, new CallbackImpl());
            System.out.println("Kafka is not waiting for Send process to complete!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
