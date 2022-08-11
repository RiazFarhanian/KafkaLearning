package org.example.riaz.kafka.lesson2;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class PhoneProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.example.riaz.kafka.lesson2.serializer.PhoneSerializer");

        try (KafkaProducer<String, Phone> kafkaProducer = new KafkaProducer<>(properties)) {
            Phone iphone = new Phone("IPhone", "Pro Max", "13", 2000d);
            ProducerRecord<String, Phone> record = new ProducerRecord<>("phones", "Iphone 13", iphone);
            RecordMetadata recordMetadata = kafkaProducer.send(record).get();
            System.out.println("Phone Record Producer TimeStamp:" + recordMetadata.timestamp());
            System.out.println("Phone Record Producer Partition:" + recordMetadata.partition());
            System.out.println("Phone Record Producer Offset:" + recordMetadata.offset());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
