package ru.nikita.lab2.dao.repository.impl;

import ru.nikita.lab2.dao.entity.UserEntity;
import ru.nikita.lab2.dao.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

public class UserRepositoryImpl extends BaseRepository implements UserRepository {
    @Override
    public UserEntity upsertUser(UserEntity user) {
        return doWithinTransaction(em -> em.merge(user));
    }

    @Override
    public void deleteUser(UserEntity user) {
        doWithinTransaction(em -> {
            em.remove(user);
            return null;
        });
    }

    @Override
    public Optional<UserEntity> findUserById(UUID userId) {
        var em = getEntityManager();
        return Optional.ofNullable(em.find(UserEntity.class, userId));
    }
}
