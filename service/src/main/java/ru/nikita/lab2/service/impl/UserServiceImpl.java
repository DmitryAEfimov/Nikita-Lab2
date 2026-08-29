package ru.nikita.lab2.service.impl;

import ru.nikita.lab2.api.dto.CreateUserDto;
import ru.nikita.lab2.api.dto.IdDto;
import ru.nikita.lab2.api.dto.UpdateUserDto;
import ru.nikita.lab2.api.dto.UserInfoDto;
import ru.nikita.lab2.dao.entity.UserEntity;
import ru.nikita.lab2.dao.repository.UserRepository;
import ru.nikita.lab2.service.UserCRUDService;
import ru.nikita.lab2.service.util.ServiceFactory;

public class UserServiceImpl implements UserCRUDService {
    private final UserRepository userRepo;

    public UserServiceImpl() {
        this.userRepo = ServiceFactory.of(UserRepository.class);
    }

    @Override
    public UserInfoDto createUser(CreateUserDto user) {
        var entity = toEntity(user);
        return doUpsert(entity);
    }

    @Override
    public UserInfoDto updateUser(UpdateUserDto user) {
        var entity = toEntity(user);
        return doUpsert(entity);
    }

    @Override
    public void removeUser(IdDto id) {
        userRepo.deleteUser(id.id());
    }

    @Override
    public UserInfoDto getUser(IdDto id) {
        return userRepo.findUserById(id.id()).map(this::fromEntity).orElse(null);
    }

    private UserInfoDto doUpsert(UserEntity user) {
        var entity = userRepo.upsertUser(user);
        return fromEntity(entity);
    }

    private UserEntity toEntity(CreateUserDto user) {
        return UserEntity.builder().login(user.login()).name(user.name()).age(user.age()).gender(user.gender()).hairColor(user.hairColor()).build();
    }

    private UserEntity toEntity(UpdateUserDto user) {
        return UserEntity.builder().id(user.userId()).name(user.name()).age(user.age()).gender(user.gender()).hairColor(user.hairColor()).build();
    }

    private UserInfoDto fromEntity(UserEntity entity) {
        return new UserInfoDto(entity.getId(), entity.getLogin(), entity.getName(), entity.getAge(), entity.getGender(), entity.getHairColor(), null, null);
    }
}
