package ru.nikita.lab2.service.impl;

import ru.nikita.lab2.api.dto.UserDto;
import ru.nikita.lab2.dao.entity.UserEntity;
import ru.nikita.lab2.dao.repository.UserRepository;
import ru.nikita.lab2.service.UserCRUDService;

public class UserServiceImpl implements UserCRUDService {
    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDto createUser(UserDto user) {
        return doUpsert(user);
    }

    @Override
    public UserDto updateUser(UserDto user) {
        return doUpsert(user);
    }

    @Override
    public void removeUser(UserDto user) {
        var entity = toEntity(user);
        userRepo.deleteUser(entity);
    }

    @Override
    public UserDto getUser(UserDto user) {
        return userRepo.findUserById(user.userId()).map(this::fromEntity).orElse(null);
    }

    private UserDto doUpsert(UserDto user) {
        var entity = toEntity(user);
        entity = userRepo.upsertUser(entity);
        return fromEntity(entity);
    }

    private UserEntity toEntity(UserDto user) {
        return UserEntity.builder()
                .id(user.userId())
                .login(user.login())
                .name(user.name())
                .age(user.age())
                .gender(user.gender())
                .hairColor(user.hairColor())
                .build();
    }

    private UserDto fromEntity(UserEntity entity) {
        return new UserDto(entity.getId(), entity.getLogin(), entity.getName(), entity.getAge(), entity.getGender(), entity.getHairColor());
    }
}
