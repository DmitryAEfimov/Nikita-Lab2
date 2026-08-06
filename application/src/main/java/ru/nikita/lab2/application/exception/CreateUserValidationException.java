package ru.nikita.lab2.application.exception;

public class CreateUserValidationException extends RuntimeException {
    public CreateUserValidationException(String message) {
        super(message);
    }
}
