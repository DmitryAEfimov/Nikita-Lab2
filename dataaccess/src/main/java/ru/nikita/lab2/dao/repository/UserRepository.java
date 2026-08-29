package ru.nikita.lab2.dao.repository;

import ru.nikita.lab2.dao.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    UserEntity upsertUser(UserEntity user);

    void deleteUser(UUID userId);

    Optional<UserEntity> findUserById(UUID userId);
}
