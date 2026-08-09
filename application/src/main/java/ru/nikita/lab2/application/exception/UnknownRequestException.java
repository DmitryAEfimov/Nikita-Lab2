package ru.nikita.lab2.application.exception;

public class UnknownRequestException extends RuntimeException {
    public UnknownRequestException(String message) {
        super(message);
    }
}
