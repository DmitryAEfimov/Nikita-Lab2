package ru.nikita.lab2.dao.repository.impl;

import ru.nikita.lab2.dao.entity.UserEntity;
import ru.nikita.lab2.dao.repository.UserRepository;
import ru.nikita.lab2.dao.repository.exception.NoUserFoundException;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

public class UserRepositoryImpl extends BaseRepository implements UserRepository {
    @Override
    public UserEntity upsertUser(UserEntity user) {
        return doWithinTransaction(em -> em.merge(user));
    }

    @Override
    public void deleteUser(UUID userId) {
        doWithinTransaction(em -> {
            Optional.ofNullable(em.find(UserEntity.class, userId)).ifPresentOrElse(em::remove, () -> {
                        throw new NoUserFoundException(userId);
                    }
            );
            return null;
        });
    }

    @Override
    public Optional<UserEntity> findUserById(UUID userId) {
        var em = getEntityManager();
        return Optional.ofNullable(em.find(UserEntity.class, userId));
    }

    @Override
    public Stream<UserEntity> findUsersById(Set<UUID> userIds) {
        var em = getEntityManager();
        return em.createNamedQuery("findByIds", UserEntity.class).getResultStream();
    }
}
