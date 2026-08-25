package ru.nikita.lab2.application.command.createuser;

import ru.nikita.lab2.api.dto.UserDto;
import ru.nikita.lab2.api.enumeration.Gender;
import ru.nikita.lab2.api.enumeration.HairColor;
import ru.nikita.lab2.application.command.Command;
import ru.nikita.lab2.application.request.RequestContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CreateUserParser implements Command {
    private final RequestContext<UserDto> ctx;

    public CreateUserParser(RequestContext<UserDto> ctx) {
        this.ctx = ctx;
    }

    @Override
    public void execute() {
        var attrs = parseAttributes();

        var login = attrs.get("login");
        var name = attrs.get("name");
        var age = Optional.ofNullable(attrs.get("age")).map(Integer::parseInt).orElse( -1);
        var gender = Optional.ofNullable(attrs.get("gender")).map(Gender::valueOf).orElse(null);
        var hairColor = Optional.ofNullable(attrs.get("hairColor")).map(HairColor::valueOf).orElse(null);

        var user = new UserDto(
                null,
                login,
                name,
                age,
                gender,
                hairColor
        );

        ctx.setPayload(user);
    }

    private Map<String, String> parseAttributes() {
        var raw = ctx.getRawPayload().trim();

        if (!raw.startsWith("{") || !raw.endsWith("}")) {
            throw new IllegalArgumentException("Invalid payload format");
        }

        raw = raw.substring(1, raw.length() - 1);

        var values = new HashMap<String, String>();
        for (var part : raw.split(",")) {
            var pair = part.split(":", 2);

            if (pair.length != 2) {
                throw new IllegalArgumentException("Invalid payload format");
            }

            var key = pair[0].trim().replace("\\\"", "");
            var value = pair[1].trim().replace("\\\"", "");

            values.put(key, value);
        }

        return values;
    }
}