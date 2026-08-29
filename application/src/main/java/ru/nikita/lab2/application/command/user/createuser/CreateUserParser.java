package ru.nikita.lab2.application.command.user.createuser;

import ru.nikita.lab2.api.dto.CreateUserDto;
import ru.nikita.lab2.api.dto.UserInfoDto;
import ru.nikita.lab2.application.command.Command;
import ru.nikita.lab2.application.request.RequestContext;
import ru.nikita.lab2.application.utils.JsonUtil;

public class CreateUserParser implements Command {
    private final RequestContext<CreateUserDto, UserInfoDto> ctx;

    public CreateUserParser(RequestContext<CreateUserDto, UserInfoDto> ctx) {
        this.ctx = ctx;
    }

    @Override
    public void execute() {
        var raw = ctx.getRawPayload();
        ctx.setPayload(JsonUtil.deserialize(raw, CreateUserDto.class));
    }
}
