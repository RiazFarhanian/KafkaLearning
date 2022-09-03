package org.example.riaz.kafka.lesson6;

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

        properties.setProperty(ProducerConfig.ACKS_CONFIG, "0");//It can be 1 or all
        properties.setProperty(ProducerConfig.BUFFER_MEMORY_CONFIG, "1048576");
        properties.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip");//It can be none or snappy
        properties.setProperty(ProducerConfig.RETRIES_CONFIG, "2");
        properties.setProperty(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, "1000");

        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "1024654");
        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "200");
        properties.setProperty(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, "200");
//        properties.setProperty(ProducerConfig., );

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
