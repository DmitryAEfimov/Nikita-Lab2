package ru.nikita.lab2.application.exception;

public class PayloadValidationException extends RuntimeException {
    public PayloadValidationException(String message) {
        super(message);
    }
}
