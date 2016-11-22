package com.baeldung.jackson.test;

import com.baeldung.jackson.dtos.withEnum.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class JacksonSerializationEnumsUnitTest {

    // tests - simple enum

    @Test
    public final void agentWithEnumTest() throws JsonParseException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final String enumAsString = mapper.writeValueAsString(new Agent("a", AgentType.AgentType1));

        System.out.println(enumAsString);
        //assertThat(enumAsString, containsString("\"name\":\"Type A\""));
    }

    @Test
    public final void whenSerializingASimpleEnum_thenCorrect() throws JsonParseException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final String enumAsString = mapper.writeValueAsString(TypeEnumSimple.TYPE1);
        System.out.println(enumAsString);

        assertThat(enumAsString, containsString("TYPE1"));
    }

    // tests - enum with main value

    @Test
    public final void whenSerializingAEnumWithValue_thenCorrect() throws JsonParseException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final String enumAsString = mapper.writeValueAsString(TypeEnumWithValue.TYPE1);
        System.out.println(enumAsString);

        assertThat(enumAsString, is("\"Type A\""));
    }

    // tests - enum

    @Test
    public final void whenSerializingAnEnum_thenCorrect() throws JsonParseException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final String enumAsString = mapper.writeValueAsString(TypeEnum.TYPE1);

        System.out.println(enumAsString);
        assertThat(enumAsString, containsString("\"name\":\"Type A\""));
    }

    @Test
    public final void whenSerializingEntityWithEnum_thenCorrect() throws JsonParseException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final String enumAsString = mapper.writeValueAsString(new MyDtoWithEnum("a", 1, true, TypeEnum.TYPE1));

        System.out.println(enumAsString);
        assertThat(enumAsString, containsString("\"name\":\"Type A\""));
    }

    @Test
    public final void whenSerializingArrayOfEnums_thenCorrect() throws JsonParseException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final String json = mapper.writeValueAsString(new TypeEnum[] { TypeEnum.TYPE1, TypeEnum.TYPE2 });

        System.out.println(json);
        assertThat(json, containsString("\"name\":\"Type A\""));
    }

    // tests - enum with custom serializer

    @Test
    public final void givenCustomSerializer_whenSerializingEntityWithEnum_thenCorrect() throws JsonParseException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final String enumAsString = mapper.writeValueAsString(new MyDtoWithEnumCustom("a", 1, true, TypeEnumWithCustomSerializer.TYPE1));

        System.out.println(enumAsString);
        assertThat(enumAsString, containsString("\"name\":\"Type A\""));
    }

}
