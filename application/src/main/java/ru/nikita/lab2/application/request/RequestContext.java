package ru.nikita.lab2.application.request;

import ru.nikita.lab2.application.command.RequestValidator;
import ru.nikita.lab2.application.command.RequestWorker;

public class RequestContext {
    private final String rawString;
    private RequestValidator validator;
    private RequestWorker worker;
    private PayloadParser parser;
    private Object payload;

    public RequestContext(String rawString) {
        this.rawString = rawString;
    }

    public String getRawString() {
        return rawString;
    }

    public RequestValidator getValidator() {
        return validator;
    }

    public RequestWorker getWorker() {
        return worker;
    }

    public Object getPayload() {
        return payload;
    }
}
