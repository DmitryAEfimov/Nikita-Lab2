package ru.nikita.lab2.service;

import ru.nikita.lab2.api.dto.IdDto;
import ru.nikita.lab2.api.dto.UserDto;

public interface UserCRUDService {
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user);
    void removeUser(IdDto id);
    UserDto getUser(IdDto id);
}
