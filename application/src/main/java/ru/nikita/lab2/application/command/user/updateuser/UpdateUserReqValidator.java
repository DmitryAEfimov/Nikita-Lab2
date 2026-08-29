package ru.nikita.lab2.application.command.user.updateuser;

import ru.nikita.lab2.api.dto.UpdateUserDto;
import ru.nikita.lab2.api.dto.UserInfoDto;
import ru.nikita.lab2.application.command.Command;
import ru.nikita.lab2.application.exception.PayloadValidationException;
import ru.nikita.lab2.application.request.RequestContext;
import ru.nikita.lab2.application.utils.ValidationUtil;

public class UpdateUserReqValidator implements Command {
    private final RequestContext<UpdateUserDto, UserInfoDto> ctx;

    public UpdateUserReqValidator(RequestContext<UpdateUserDto, UserInfoDto> ctx) {
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
