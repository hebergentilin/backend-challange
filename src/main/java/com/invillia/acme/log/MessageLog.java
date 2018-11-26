package com.invillia.acme.log;

import org.slf4j.MDC;

import java.util.stream.Collectors;

public enum MessageLog {
    BC0001("backend-challenge load properties from classpath"),
    BC0002("creating new provider", "provider={}"),
    BC0003("provider invalid", "errors={}"),
    BC0004("saving provider"),
    BC0005("provider not found", "providerId={}"),
    BC0006("updating provider", "provider={}"),
    BC0007("failed to update provider");

    private final String shortMessage;
    private final String params;

    MessageLog(String shortMessage) {
        this.params = null;
        this.shortMessage = shortMessage;
    }

    MessageLog(String shortMessage, String log) {
        this.params = log;
        this.shortMessage = shortMessage;
    }

    public String text() {
        MDC.put("short_message", String.format("\"%s\"", this.shortMessage.trim()));
        MDC.put("name", this.name());
        if (this.params == null) {
            return String.format("name=%s", this.name());
        } else {
            return String.format("%s, name=%s", this.params, this.name());
        }
    }
}
