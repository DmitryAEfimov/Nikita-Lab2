package ru.nikita.lab2.application.command;

import ru.nikita.lab2.application.request.RequestContext;

public class CmdParser implements Command {
    private final RequestContext ctx;

    public CmdParser(RequestContext ctx) {
        this.ctx = ctx;
    }
}
