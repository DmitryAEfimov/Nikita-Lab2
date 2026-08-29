package ru.nikita.lab2.application.command.user.createuser;

import ru.nikita.lab2.api.dto.CreateUserDto;
import ru.nikita.lab2.api.dto.UserInfoDto;
import ru.nikita.lab2.application.request.RequestContext;
import ru.nikita.lab2.application.request.RequestProcessor;

import java.util.List;

public class CreateUserProcessor extends RequestProcessor<RequestContext<CreateUserDto, UserInfoDto>> {
    private final RequestContext<CreateUserDto, UserInfoDto> ctx;

    public CreateUserProcessor(RequestContext<CreateUserDto, UserInfoDto> ctx) {
        super(List.of(new CreateUserParser(ctx), new CreateUserReqValidator(ctx), new CreateUserWorker(ctx)));
        this.ctx = ctx;
    }

    @Override
    public RequestContext<CreateUserDto, UserInfoDto> getContext() {
        return ctx;
    }
}
