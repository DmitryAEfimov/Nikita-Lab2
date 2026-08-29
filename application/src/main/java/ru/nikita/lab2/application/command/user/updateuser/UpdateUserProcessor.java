package ru.nikita.lab2.application.command.user.updateuser;

import ru.nikita.lab2.api.dto.UpdateUserDto;
import ru.nikita.lab2.api.dto.UserInfoDto;
import ru.nikita.lab2.application.request.RequestContext;
import ru.nikita.lab2.application.request.RequestProcessor;

import java.util.List;

public class UpdateUserProcessor extends RequestProcessor<RequestContext<UpdateUserDto, UserInfoDto>> {
    private final RequestContext<UpdateUserDto, UserInfoDto> ctx;

    public UpdateUserProcessor(RequestContext<UpdateUserDto, UserInfoDto> ctx) {
        super(List.of(new UpdateUserParser(ctx), new UpdateUserReqValidator(ctx), new UpdateUserWorker(ctx)));
        this.ctx = ctx;
    }

    @Override
    public RequestContext<UpdateUserDto, UserInfoDto> getContext() {
        return ctx;
    }
}
