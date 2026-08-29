package ru.nikita.lab2.application.command.user.deleteuser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nikita.lab2.api.dto.IdDto;
import ru.nikita.lab2.application.command.Command;
import ru.nikita.lab2.application.request.RequestContext;
import ru.nikita.lab2.service.UserCRUDService;
import ru.nikita.lab2.service.util.ServiceFactory;

public class DeleteUserWorker implements Command {
    private static final Logger logger = LoggerFactory.getLogger(DeleteUserWorker.class);

    private final RequestContext<IdDto, Void> ctx;
    private final UserCRUDService userService;

    public DeleteUserWorker(RequestContext<IdDto, Void> ctx) {
        this.ctx = ctx;
        this.userService = ServiceFactory.of(UserCRUDService.class);
    }

    @Override
    public void execute() {
        var id = ctx.getPayload();
        userService.removeUser(id);
        logger.info("User removed successfully: {}", id.id());
    }
}
