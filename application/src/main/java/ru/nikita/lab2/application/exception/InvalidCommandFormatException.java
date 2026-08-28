package ru.nikita.lab2.application.exception;

public class InvalidCommandFormatException extends RuntimeException {
    private static final String INVALID_COMMAND_ERR_TEMPLATE = "Command and payload must be separated by space(s): %s";

    public InvalidCommandFormatException(String invalidCommand) {
        super(String.format(INVALID_COMMAND_ERR_TEMPLATE, invalidCommand));
    }

}
