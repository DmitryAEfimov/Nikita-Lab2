package ru.nikita.lab2.application.request;

import ru.nikita.lab2.application.command.user.createuser.CreateUserProcessor;
import ru.nikita.lab2.application.command.user.deleteuser.DeleteUserProcessor;
import ru.nikita.lab2.application.command.user.updateuser.UpdateUserProcessor;
import ru.nikita.lab2.application.exception.InvalidCommandFormatException;
import ru.nikita.lab2.application.exception.UnknownRequestException;

public class RequestDispatcher {
    public static RequestProcessor<?> dispatch(String input) {
        var parts = input.trim().split("\\s+", 2);

        if (parts.length != 2) {
            throw new InvalidCommandFormatException(input);
        }

        var commandName = parts[0];
        var rawPayload = parts[1];
        return switch (commandName) {
            case "createUser" -> new CreateUserProcessor(new RequestContext<>(commandName, rawPayload));
            case "updateUser" -> new UpdateUserProcessor(new RequestContext<>(commandName, rawPayload));
            case "deleteUser" -> new DeleteUserProcessor(new RequestContext<>(commandName, rawPayload));
            //            case "readUserInfo" -> {
            //                var ctx = new RequestContext<UserDto>(commandName, rawPayload);
            //                new CreateUserProcessor(ctx);
            //            }
            //            case "addFriends" -> {
            //                var ctx = new RequestContext<UserDto>(commandName, rawPayload);
            //                new CreateUserProcessor(ctx);
            //            }
            //            case "deleteFriends" -> {
            //                var ctx = new RequestContext<UserDto>(commandName, rawPayload);
            //                new CreateUserProcessor(ctx);
            //            }
            //            case "createAccount" -> {
            //                var ctx = new RequestContext<UserDto>(commandName, rawPayload);
            //                new CreateUserProcessor(ctx);
            //            }
            //            case "deleteAccount" -> {
            //                var ctx = new RequestContext<UserDto>(commandName, rawPayload);
            //                new CreateUserProcessor(ctx);
            //            }
            //            case "readAccountInfo" -> {
            //                var ctx = new RequestContext<UserDto>(commandName, rawPayload);
            //                new CreateUserProcessor(ctx);
            //            }
            //            case "showHistory" -> {
            //                var ctx = new RequestContext<UserDto>(commandName, rawPayload);
            //                new CreateUserProcessor(ctx);
            //            }
            //            case "deposit" -> {
            //                var ctx = new RequestContext<UserDto>(commandName, rawPayload);
            //                new CreateUserProcessor(ctx);
            //            }
            //            case "withdraw" -> {
            //                var ctx = new RequestContext<UserDto>(commandName, rawPayload);
            //                new CreateUserProcessor(ctx);
            //            }
            //            case "transfer" -> {
            //                var ctx = new RequestContext<UserDto>(commandName, rawPayload);
            //                new CreateUserProcessor(ctx);
            //            }
            default -> throw new UnknownRequestException(commandName);
        };
    }
}
