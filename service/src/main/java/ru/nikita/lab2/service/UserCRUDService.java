package ru.nikita.lab2.service;

import ru.nikita.lab2.api.dto.UserDto;

import java.util.UUID;

public interface UserCRUDService {
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user);
    void removeUser(UUID userId);
    UserDto getUser(UserDto user);
}
