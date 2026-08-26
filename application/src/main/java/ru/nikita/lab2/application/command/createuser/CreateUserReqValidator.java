package ru.nikita.lab2.application.command.createuser;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Validator;
import ru.nikita.lab2.api.dto.UserDto;
import ru.nikita.lab2.application.command.Command;
import ru.nikita.lab2.application.request.RequestContext;

public class CreateUserReqValidator implements Command {
    private final RequestContext<UserDto> ctx;
    private final Validator<UserDto> validator;

    public CreateUserReqValidator(RequestContext<UserDto> ctx) {
        this.ctx = ctx;
        this.validator = ValidatorBuilder.of(UserDto.class)
                .constraint(UserDto::age, c -> c.)
//                .constraint(notBlankPerson::age, c -> c.positiveOrZero().lessThan(150))
                .build();
    }

    @Override
    public void execute() {
        var obj = ctx.getPayload();
        var result = validator.validate(obj);
    }
}
