package org.example.riaz.kafka.lesson5;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class PhonePartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        if (topic.equalsIgnoreCase("phones")) {
            if (value.toString().toLowerCase().contains("lumia")) {
                return 1;
            } else if (value.toString().toLowerCase().contains("galaxy")) {
                return 2;
            } else if (value.toString().toLowerCase().contains("iphone")) {
                return 3;
            }
        }
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
