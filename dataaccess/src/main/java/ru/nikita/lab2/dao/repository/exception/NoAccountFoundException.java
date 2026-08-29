package ru.nikita.lab2.dao.repository.exception;

public class NoAccountFoundException extends RuntimeException {
    public NoAccountFoundException() {
        super("Account not found");
    }

    public NoAccountFoundException(String message) {
        super(message);
    }
}