package ru.nikita.lab2.application.command.user.updateuser;

import ru.nikita.lab2.api.dto.UpdateUserDto;
import ru.nikita.lab2.api.dto.UserInfoDto;
import ru.nikita.lab2.application.command.Command;
import ru.nikita.lab2.application.request.RequestContext;
import ru.nikita.lab2.application.utils.JsonUtil;

public class UpdateUserParser implements Command {
    private final RequestContext<UpdateUserDto, UserInfoDto> ctx;

    public UpdateUserParser(RequestContext<UpdateUserDto, UserInfoDto> ctx) {
        this.ctx = ctx;
    }

    @Override
    public void execute() {
        var raw = ctx.getRawPayload();
        ctx.setPayload(JsonUtil.deserialize(raw, UpdateUserDto.class));
    }
}
