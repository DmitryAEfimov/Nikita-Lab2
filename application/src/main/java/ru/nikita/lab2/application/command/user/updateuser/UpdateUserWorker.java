package ru.nikita.lab2.application.command.user.updateuser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nikita.lab2.api.dto.UpdateUserDto;
import ru.nikita.lab2.api.dto.UserInfoDto;
import ru.nikita.lab2.application.command.Command;
import ru.nikita.lab2.application.request.RequestContext;
import ru.nikita.lab2.service.UserCRUDService;
import ru.nikita.lab2.service.util.ServiceFactory;

public class UpdateUserWorker implements Command {
    private static final Logger logger = LoggerFactory.getLogger(UpdateUserWorker.class);

    private final RequestContext<UpdateUserDto, UserInfoDto> ctx;
    private final UserCRUDService userService;

    public UpdateUserWorker(RequestContext<UpdateUserDto, UserInfoDto> ctx) {
        this.ctx = ctx;
        this.userService = ServiceFactory.of(UserCRUDService.class);
    }

    @Override
    public void execute() {
        var user = ctx.getPayload();
        var updatedUser = userService.updateUser(user);
        logger.info("User updated successfully: {}", updatedUser.userId());
        ctx.setResult(updatedUser);
    }
}
