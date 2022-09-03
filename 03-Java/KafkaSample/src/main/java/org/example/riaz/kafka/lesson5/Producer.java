package org.example.riaz.kafka.lesson5;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;

public class Producer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("partitioner.class", PhonePartitioner.class.getName());

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(UUID.randomUUID().toString(), "Galaxy A23 5G");
        hashMap.put(UUID.randomUUID().toString(), "Lumia 650");
        hashMap.put(UUID.randomUUID().toString(), "IPhone 13");
        hashMap.put(UUID.randomUUID().toString(), "Galaxy M13");
        hashMap.put(UUID.randomUUID().toString(), "Lumia 950");
        hashMap.put(UUID.randomUUID().toString(), "Galaxy A53 5G");
        hashMap.put(UUID.randomUUID().toString(), "Lumia 532");
        hashMap.put(UUID.randomUUID().toString(), "IPhone SE");
        hashMap.put(UUID.randomUUID().toString(), "Lumia 650");

        hashMap.keySet().forEach(key -> publish(properties, key, hashMap.get(key)));
    }

    private static void publish(Properties properties, String key, String value) {
        try (KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties)) {
            ProducerRecord<String, String> record = new ProducerRecord<>("Phones", key, value);
            RecordMetadata recordMetadata = kafkaProducer.send(record).get();
            System.out.println("TimeStamp:" + recordMetadata.timestamp());
            System.out.println("Partition:" + recordMetadata.partition());
            System.out.println("Offset:" + recordMetadata.offset());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
