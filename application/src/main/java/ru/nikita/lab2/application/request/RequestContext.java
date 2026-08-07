package ru.nikita.lab2.application.request;

public class RequestContext<T> {
    private final String commandName;
    private final String rawPayload;
    private T payload;

    public RequestContext(String commandName, String rawPayload) {
        this.commandName = commandName;
        this.rawPayload = rawPayload;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getRawPayload() {
        return rawPayload;
    }

    public T getPayload() {
        return payload;
    }
}
