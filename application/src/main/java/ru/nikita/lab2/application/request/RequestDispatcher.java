package ru.nikita.lab2.application.request;

import ru.nikita.lab2.api.dto.UserDto;
import ru.nikita.lab2.application.command.createuser.CreateUserProcessor;

public class RequestDispatcher {
    public static RequestProcessor<?> dispatch(String input) {
        var parts = input.split("\\s");
        if (parts.length != 2) {
            throw new InvalidCommandFormatException(input);
        }

        var commandName = parts[0];
        var rawPayload = parts[1];
        return switch (commandName) {
            case "createUser" -> {
                var ctx = new RequestContext<UserDto>(commandName, rawPayload);
                new CreateUserProcessor(ctx);
            }
            case "updateUser" -> new UpdateUserProcessor(parts[1]);
            case "deleteUser" -> new DeleteUserProcessor(parts[1]);
            case "readUserInfo" -> new ReadUserInfoProcessor(parts[1]);
            case "addFriend" -> new AddFriendProcessor(parts[1]);
            case "deleteFriend" -> new DeleteFriendProcessor(parts[1]);
            case "createAccount" -> new CreateAccountProcessor(parts[1]);
            case "deleteAccount" -> new DeleteAccountProcessor(parts[1]);
            case "readAccountInfo" -> new ReadAccountInfoProcessor(parts[1]);
            case "showHistory" -> new ShowHisoryProcessor(parts[1]);
            case "deposit" -> new DepositProcessor(parts[1]);
            case "withdraw" -> new WithdrawProcessor(parts[1]);
            case "transfer" -> new TransferProcessor(parts[1]);
            throw new UnknownRequestException(parts[0]);
        };
    }
}
