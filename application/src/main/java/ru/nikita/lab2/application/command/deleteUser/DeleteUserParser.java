package ru.nikita.lab2.application.command.deleteUser;

import ru.nikita.lab2.api.dto.IdDto;
import ru.nikita.lab2.application.command.Command;
import ru.nikita.lab2.application.request.RequestContext;
import ru.nikita.lab2.application.utils.JsonUtil;

public class DeleteUserParser implements Command {
    private final RequestContext<IdDto> ctx;

    public DeleteUserParser(RequestContext<IdDto> ctx) {
        this.ctx = ctx;
    }

    @Override
    public void execute() {
        var raw = ctx.getRawPayload();
        ctx.setPayload(JsonUtil.deserialize(raw, IdDto.class));
    }
}
