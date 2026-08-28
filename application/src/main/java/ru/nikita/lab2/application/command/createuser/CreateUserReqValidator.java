package ru.nikita.lab2.application.command.createuser;

import ru.nikita.lab2.api.dto.UserDto;
import ru.nikita.lab2.application.command.Command;
import ru.nikita.lab2.application.exception.PayloadValidationException;
import ru.nikita.lab2.application.request.RequestContext;
import ru.nikita.lab2.application.utils.ValidationUtil;

public class CreateUserReqValidator implements Command {
    private final RequestContext<UserDto> ctx;

    public CreateUserReqValidator(RequestContext<UserDto> ctx) {
        this.ctx = ctx;
    }

    @Override
    public void execute() {
        var obj = ctx.getPayload();
        var result = ValidationUtil.validate(obj);

        if (result != null) {
            throw new PayloadValidationException(result);
        }
    }
}
