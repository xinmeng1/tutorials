package com.baeldung.jackson.dtos.withEnum;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * User:    mengxin
 * Date:    21/11/16
 * Project: jackson
 */
public enum AgentType {
    AgentType1("AgentType1_ID", 1),
    AgentType2("AgentType2_ID");


    AgentType(String msg, int maxApps, boolean hidden) {
        this.message = msg;
        this.maxApplications = maxApps;
        this.hidden = hidden;
    }
    AgentType(String msg, int maxApps) {
        this.message = msg;
        this.maxApplications = maxApps;
        this.hidden = false;
    }
    AgentType(String msg) {
        this.message = msg;
        this.maxApplications = -1;
        this.hidden = false;
    }

    private final boolean hidden;

    private final String message;
    private final Integer maxApplications;
    public String message() { return message; }

    public boolean isHidden() {
        return hidden;
    }
    @JsonValue
    @JsonSerialize(using = IntToStringSerializer.class, as=String.class)
    public String getMessage() {
        return message;
    }

    public int getMaxApplications() {
        return maxApplications;
    }


//    @JsonValue
//    public String toValue() {
//        return this.getMessage();
//    }

}
