package org.example.riaz.kafka.lesson2.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.example.riaz.kafka.lesson2.Phone;

import java.io.IOException;

public class PhoneDeserializer implements Deserializer<Phone> {
    @Override
    public Phone deserialize(String topic, byte[] bytes) {
        Phone result = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.readValue(bytes, Phone.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
