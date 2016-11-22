package com.baeldung.jackson.dtos.withEnum;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * User:    mengxin
 * Date:    21/11/16
 * Project: jackson
 */
public class IntToStringSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String tmpInt,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeObject(tmpInt+"_translation");
    }

}