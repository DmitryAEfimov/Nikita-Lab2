package ru.nikita.lab2.dao.repository;

import ru.nikita.lab2.dao.entity.UserEntity;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

public interface UserRepository {
    UserEntity upsertUser(UserEntity user);

    void deleteUser(UUID userId);

    Optional<UserEntity> findUserById(UUID userId);

    Stream<UserEntity> findUsersById(Set<UUID> userIds);
}
