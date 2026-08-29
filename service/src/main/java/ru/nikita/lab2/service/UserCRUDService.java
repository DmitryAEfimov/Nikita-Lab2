package ru.nikita.lab2.service;

import ru.nikita.lab2.api.dto.IdDto;
import ru.nikita.lab2.api.dto.CreateUserDto;
import ru.nikita.lab2.api.dto.UpdateUserDto;
import ru.nikita.lab2.api.dto.UserInfoDto;

public interface UserCRUDService {
    UserInfoDto createUser(CreateUserDto user);
    UserInfoDto updateUser(UpdateUserDto user);
    void removeUser(IdDto id);
    UserInfoDto getUser(IdDto id);
}
