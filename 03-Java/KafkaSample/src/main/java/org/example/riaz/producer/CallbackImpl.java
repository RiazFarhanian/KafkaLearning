package org.example.riaz.producer;

import org.apache.kafka.clients.producer.RecordMetadata;

public class CallbackImpl implements org.apache.kafka.clients.producer.Callback {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        System.out.println("Async Producer TimeStamp:" + recordMetadata.timestamp());
        System.out.println("Async Producer Partition:" + recordMetadata.partition());
        System.out.println("Async Producer Offset:" + recordMetadata.offset());

    }
}
