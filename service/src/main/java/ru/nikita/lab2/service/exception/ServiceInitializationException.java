package ru.nikita.lab2.service.exception;

public class ServiceInitializationException extends RuntimeException {
    private static final String INITIALIZATION_ERR_TEMPLATE = "Can't instantiate service %s";
    public ServiceInitializationException(Class<?> serviceClass, Throwable cause) {
        super(String.format(INITIALIZATION_ERR_TEMPLATE, serviceClass.getName()), cause);
    }
}
