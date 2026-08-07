package ru.nikita.lab2.application.command.createuser;

import ru.nikita.lab2.api.dto.UserDto;
import ru.nikita.lab2.application.request.RequestContext;
import ru.nikita.lab2.application.request.RequestProcessor;

import java.util.List;

public class CreateUserProcessor extends RequestProcessor<RequestContext<UserDto>> {
    private final RequestContext<UserDto> ctx;

    public CreateUserProcessor(RequestContext<UserDto> ctx) {
        super(List.of(new CreateUserParser(ctx), new CreateUserReqValidator(ctx), new CreateUserWorker(ctx)));
        this.ctx = ctx;
    }

    @Override
    public RequestContext<UserDto> getContext() {
        return ctx;
    }
}
