package ru.nikita.lab2.application.command.createuser;

import ru.nikita.lab2.api.dto.UserDto;
import ru.nikita.lab2.application.command.Command;
import ru.nikita.lab2.application.request.RequestContext;
import ru.nikita.lab2.application.utils.JsonUtil;

public class CreateUserParser implements Command {
    private final RequestContext<UserDto> ctx;

    public CreateUserParser(RequestContext<UserDto> ctx) {
        this.ctx = ctx;
    }

    @Override
    public void execute() {
        var raw = ctx.getRawPayload();
        ctx.setPayload(JsonUtil.deserialize(raw, UserDto.class));
    }
}
