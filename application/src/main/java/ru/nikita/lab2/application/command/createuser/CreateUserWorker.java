package ru.nikita.lab2.application.command.createuser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nikita.lab2.api.dto.UserDto;
import ru.nikita.lab2.application.command.Command;
import ru.nikita.lab2.application.request.RequestContext;
import ru.nikita.lab2.service.UserCRUDService;
import ru.nikita.lab2.service.util.ServiceFactory;

public class CreateUserWorker implements Command {
    private static final Logger logger = LoggerFactory.getLogger(CreateUserWorker.class);

    private final RequestContext<UserDto> ctx;
    private final UserCRUDService userService;

    public CreateUserWorker(RequestContext<UserDto> ctx) {
        this.ctx = ctx;
        this.userService = ServiceFactory.of(UserCRUDService.class);
    }

    @Override
    public void execute() {
        var createdUser = userService.createUser(ctx.getPayload());
        logger.info("User created successfully: {}", createdUser.userId());
    }
}
