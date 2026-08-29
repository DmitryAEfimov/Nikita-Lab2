package ru.nikita.lab2.application.request;

public class RequestContext<Req, Res> {
    private final String commandName;
    private final String rawPayload;
    private Req payload;
    private Res result;

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

    public Req getPayload() {
        return payload;
    }

    public void setPayload(Req payload) {
        this.payload = payload;
    }

    public Res getResult() {
        return result;
    }

    public void setResult(Res result) {
        this.result = result;
    }
}
