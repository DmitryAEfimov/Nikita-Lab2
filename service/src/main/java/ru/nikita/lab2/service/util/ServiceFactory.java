package ru.nikita.lab2.service.util;

import ru.nikita.lab2.dao.repository.AccountRepository;
import ru.nikita.lab2.dao.repository.UserRepository;
import ru.nikita.lab2.dao.repository.impl.AccountRepositoryImpl;
import ru.nikita.lab2.dao.repository.impl.UserRepositoryImpl;
import ru.nikita.lab2.service.AccountCRUDService;
import ru.nikita.lab2.service.FriendService;
import ru.nikita.lab2.service.OperationService;
import ru.nikita.lab2.service.UserCRUDService;
import ru.nikita.lab2.service.exception.ServiceInitializationException;
import ru.nikita.lab2.service.impl.AccountServiceImpl;
import ru.nikita.lab2.service.impl.FriendServiceImpl;
import ru.nikita.lab2.service.impl.OperationServiceImpl;
import ru.nikita.lab2.service.impl.UserServiceImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public final class ServiceFactory {
    private final static Map<Class<?>, Class<?>> SERVICE_IMPLEMENTATION_CLASSES = Map.of(
            UserCRUDService.class, UserServiceImpl.class,
            AccountCRUDService.class, AccountServiceImpl.class,
            FriendService.class, FriendServiceImpl.class,
            OperationService.class, OperationServiceImpl.class,
            AccountRepository.class, AccountRepositoryImpl.class,
            UserRepository.class, UserRepositoryImpl.class);
    private final static Map<Class<?>, Object> SERVICE_IMPLEMENTATIONS = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> T of(Class<T> serviceClass) {
        var impl = SERVICE_IMPLEMENTATIONS.get(serviceClass);
        if (impl == null) {
            try {
                impl = SERVICE_IMPLEMENTATION_CLASSES.get(serviceClass).getDeclaredConstructor().newInstance();
                SERVICE_IMPLEMENTATIONS.put(serviceClass, impl);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new ServiceInitializationException(serviceClass, e);
            }
        }

        return (T) impl;
    }
}