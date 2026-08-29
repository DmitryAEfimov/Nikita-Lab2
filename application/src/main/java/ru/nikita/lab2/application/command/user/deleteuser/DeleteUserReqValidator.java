package ru.nikita.lab2.application.command.user.deleteuser;

import ru.nikita.lab2.api.dto.IdDto;
import ru.nikita.lab2.application.command.Command;
import ru.nikita.lab2.application.exception.PayloadValidationException;
import ru.nikita.lab2.application.request.RequestContext;
import ru.nikita.lab2.application.utils.ValidationUtil;

public class DeleteUserReqValidator implements Command {
    private final RequestContext<IdDto, Void> ctx;

    public DeleteUserReqValidator(RequestContext<IdDto, Void> ctx) {
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
