package ru.nikita.lab2.application.command.user.deleteuser;

import ru.nikita.lab2.api.dto.IdDto;
import ru.nikita.lab2.application.request.RequestContext;
import ru.nikita.lab2.application.request.RequestProcessor;

import java.util.List;

public class DeleteUserProcessor extends RequestProcessor<RequestContext<IdDto, Void>> {
    private final RequestContext<IdDto, Void> ctx;

    public DeleteUserProcessor(RequestContext<IdDto, Void> ctx) {
        super(List.of(new DeleteUserParser(ctx), new DeleteUserReqValidator(ctx), new DeleteUserWorker(ctx)));
        this.ctx = ctx;
    }

    @Override
    public RequestContext<IdDto, Void> getContext() {
        return ctx;
    }
}
