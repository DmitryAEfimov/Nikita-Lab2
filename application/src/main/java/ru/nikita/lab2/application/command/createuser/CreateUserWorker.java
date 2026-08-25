package ru.nikita.lab2.application.command.createuser;

import ru.nikita.lab2.api.dto.UserDto;
import ru.nikita.lab2.application.command.Command;
import ru.nikita.lab2.application.request.RequestContext;
import ru.nikita.lab2.service.UserCRUDService;

public class CreateUserWorker implements Command {
    private final RequestContext<UserDto> ctx;
    private final UserCRUDService userService;

    public CreateUserWorker(
            RequestContext<UserDto> ctx,
            UserCRUDService userService
    ) {
        this.ctx = ctx;
        this.userService = userService;
    }

    @Override
    public void execute() {
        UserDto createdUser = userService.createUser(ctx.getPayload());

        ctx.setPayload(createdUser);

        System.out.println(
                "User created successfully: " + createdUser.userId()
        );
    }
}