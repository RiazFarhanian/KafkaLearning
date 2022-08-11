package org.example.riaz.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class SyncProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.DoubleSerializer");

        try (KafkaProducer<String, Double> kafkaProducer = new KafkaProducer<>(properties)) {
            ProducerRecord<String, Double> record = new ProducerRecord<>("price", "Iphone 13", 12000d);
            RecordMetadata recordMetadata = kafkaProducer.send(record).get();
            System.out.println("Sync Producer TimeStamp:" + recordMetadata.timestamp());
            System.out.println("Sync Producer Partition:" + recordMetadata.partition());
            System.out.println("Sync Producer Offset:" + recordMetadata.offset());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
