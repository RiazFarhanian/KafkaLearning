package org.example.riaz.kafka.lesson2.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.example.riaz.kafka.lesson2.Phone;

public class PhoneSerializer implements Serializer<Phone> {
    @Override
    public byte[] serialize(String topic, Phone phone) {
        byte[] response = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            response = mapper.writeValueAsString(phone).getBytes();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }
}
