package org.example.riaz.kafka.lesson3.producer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.example.riaz.kafka.lesson3.Order;

import java.util.Properties;

public class OrderProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", KafkaAvroSerializer.class.getName());
        properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        properties.setProperty("schema.registry.url", "http://localhost:8081");

        try (KafkaProducer<String, Order> kafkaProducer = new KafkaProducer<>(properties)) {
            Order order = new Order("John", "Tesla", 1);

            ProducerRecord<String, Order> record = new ProducerRecord<>("OrderAvroTopic", order.getCustomer().toString(), order);
            RecordMetadata recordMetadata = kafkaProducer.send(record).get();
            System.out.println("Phone Record Producer TimeStamp:" + recordMetadata.timestamp());
            System.out.println("Phone Record Producer Partition:" + recordMetadata.partition());
            System.out.println("Phone Record Producer Offset:" + recordMetadata.offset());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
