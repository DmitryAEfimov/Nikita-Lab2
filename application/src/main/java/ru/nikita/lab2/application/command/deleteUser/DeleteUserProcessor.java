package ru.nikita.lab2.application.command.deleteUser;

import ru.nikita.lab2.api.dto.IdDto;
import ru.nikita.lab2.application.request.RequestContext;
import ru.nikita.lab2.application.request.RequestProcessor;

import java.util.List;

public class DeleteUserProcessor extends RequestProcessor<RequestContext<IdDto>> {
    private final RequestContext<IdDto> ctx;

    public DeleteUserProcessor(RequestContext<IdDto> ctx) {
        super(List.of(new DeleteUserParser(ctx), new DeleteUserReqValidator(ctx), new DeleteUserWorker(ctx)));
        this.ctx = ctx;
    }

    @Override
    public RequestContext<IdDto> getContext() {
        return ctx;
    }
}
