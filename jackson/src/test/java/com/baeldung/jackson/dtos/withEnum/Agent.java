package com.baeldung.jackson.dtos.withEnum;

/**
 * User:    mengxin
 * Date:    21/11/16
 * Project: jackson
 */
public class Agent {
    private String name;
    //@JsonSerialize(using = IntToStringSerializer.class, as=String.class)
    private AgentType agentType;

    public Agent(String name, AgentType agentType) {
        this.name = name;
        this.agentType = agentType;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AgentType getAgentType() {
        return agentType;
    }

    public void setAgentType(AgentType agentType) {
        this.agentType = agentType;
    }
}

//class IntToStringSerializer extends JsonSerializer<String> {
//
//    @Override
//    public void serialize(String tmpInt,
//                          JsonGenerator jsonGenerator,
//                          SerializerProvider serializerProvider)
//            throws IOException, JsonProcessingException {
//        jsonGenerator.writeObject(tmpInt+"_translation");
//    }
//
//}