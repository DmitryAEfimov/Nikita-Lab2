package ru.nikita.lab2.application.command.createuser;

import ru.nikita.lab2.application.command.Command;
import ru.nikita.lab2.application.request.RequestContext;

public class CreateUserReqValidator implements Command {
    private final RequestContext ctx;

    public CreateUserReqValidator(RequestContext ctx) {
        this.ctx = ctx;
    }
}
