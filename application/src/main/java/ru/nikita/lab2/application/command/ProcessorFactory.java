package ru.nikita.lab2.application.command;

import ru.nikita.lab2.application.request.RequestContext;

public interface ProcessorFactory {
    PayloadParser parserOf(RequestContext ctx);
    RequestValidator validatorOf(RequestContext ctx);
    RequestWorker workerOf(RequestContext ctx);
}
