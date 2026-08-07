package ru.nikita.lab2.application.request;

import ru.nikita.lab2.application.command.Command;

import java.util.List;

public abstract class RequestProcessor<T extends RequestContext<?>> implements Command {
    private final List<Command> chain;

    public RequestProcessor(List<Command> chain) {
        this.chain = chain;
    }

    @Override
    public void execute() {
        chain.forEach(Command::execute);
    }

    public abstract T getContext();
}
