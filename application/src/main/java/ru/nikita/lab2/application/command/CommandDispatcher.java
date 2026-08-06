package ru.nikita.lab2.application.command;

import ru.nikita.lab2.application.request.RequestContext;

public class CommandDispatcher implements Command {
    private RequestContext ctx;

    public CommandDispatcher(String rawCommand) {
        this.ctx = new RequestContext(rawCommand);
    }

    @Override
    public void execute() {
        new CmdParser(ctx).execute();
    }
}
