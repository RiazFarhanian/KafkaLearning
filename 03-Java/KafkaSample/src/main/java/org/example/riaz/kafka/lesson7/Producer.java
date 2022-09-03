package org.example.riaz.kafka.lesson7;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.DoubleSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Producer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, DoubleSerializer.class.getName());

        properties.setProperty(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "producer-test");

        KafkaProducer<String, Double> kafkaProducer = new KafkaProducer<>(properties);
        kafkaProducer.initTransactions();
        ProducerRecord<String, Double> record1 = new ProducerRecord<>("price", "Iphone 13", 12000d);
        ProducerRecord<String, Double> record2 = new ProducerRecord<>("price", "Mac Book Pro", 110000d);

        try {
            kafkaProducer.beginTransaction();
            kafkaProducer.send(record1);
            kafkaProducer.send(record2);
            kafkaProducer.commitTransaction();
        } catch (Exception e) {
            kafkaProducer.abortTransaction();
        }
    }
}
