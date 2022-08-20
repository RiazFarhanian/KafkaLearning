package org.example.riaz.kafka.lesson4.producer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class OrderProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", KafkaAvroSerializer.class.getName());
        properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        properties.setProperty("schema.registry.url", "http://localhost:8081");
        Schema.Parser parser = new Schema.Parser();

        Schema schema = parser.parse("{\n" +
                "    \"namespace\": \"org.example.riaz.kafka.lesson4\",\n" +
                "    \"type\": \"record\",\n" +
                "    \"name\":\"Order\",\n" +
                "    \"fields\":[\n" +
                "        {\"name\":\"customer\", \"type\":\"string\"},\n" +
                "        {\"name\":\"product\", \"type\":\"string\"},\n" +
                "        {\"name\":\"quantity\", \"type\":\"int\"}\n" +
                "    ]\n" +
                "}");

        try (KafkaProducer<String, GenericRecord> kafkaProducer = new KafkaProducer<>(properties)) {
            GenericRecord order = new GenericData.Record(schema);
            order.put("customer", "John");
            order.put("product", "Tesla");
            order.put("quantity", 1);

            ProducerRecord<String, GenericRecord> record = new ProducerRecord<>("OrderAvroGRTopic", order.get("customer").toString(), order);
            RecordMetadata recordMetadata = kafkaProducer.send(record).get();
            System.out.println("Phone Record Producer TimeStamp:" + recordMetadata.timestamp());
            System.out.println("Phone Record Producer Partition:" + recordMetadata.partition());
            System.out.println("Phone Record Producer Offset:" + recordMetadata.offset());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
