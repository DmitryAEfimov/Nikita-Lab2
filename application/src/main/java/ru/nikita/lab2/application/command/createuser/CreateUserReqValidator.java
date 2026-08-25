package ru.nikita.lab2.application.command.createuser;

import ru.nikita.lab2.api.dto.UserDto;
import ru.nikita.lab2.application.command.Command;
import ru.nikita.lab2.application.exception.CreateUserValidationException;
import ru.nikita.lab2.application.request.RequestContext;

public class CreateUserReqValidator implements Command {
    private final RequestContext<UserDto> ctx;

    public CreateUserReqValidator(RequestContext<UserDto> ctx) {
        this.ctx = ctx;
    }

    @Override
    public void execute() {
        var user = ctx.getPayload();

        if (user == null) {
            throw new CreateUserValidationException("User payload is missing");
        }

        if (user.login() == null || user.login().isBlank()) {
            throw new CreateUserValidationException("Login must not be empty");
        }

        if (user.name() == null || user.name().isBlank()) {
            throw new CreateUserValidationException("Name must not be empty");
        }

        if (user.age() <= 0) {
            throw new CreateUserValidationException("Age must be positive");
        }
    }
}