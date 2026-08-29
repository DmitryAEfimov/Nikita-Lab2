package ru.nikita.lab2.service.impl;

import ru.nikita.lab2.api.dto.ChangeFriendsDto;
import ru.nikita.lab2.dao.entity.UserEntity;
import ru.nikita.lab2.dao.repository.UserRepository;
import ru.nikita.lab2.dao.repository.exception.NoUserFoundException;
import ru.nikita.lab2.service.FriendService;
import ru.nikita.lab2.service.util.ServiceFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class FriendServiceImpl implements FriendService {
    private final UserRepository userRepo;

    public FriendServiceImpl() {
        this.userRepo = ServiceFactory.of(UserRepository.class);
    }

    @Override
    public void addFriends(ChangeFriendsDto friends) {
        var user = doFindUser(friends.userId());
        var newFriends = doFindUsers(friends.friendIds());

        var totalFriends = new HashSet<>(user.getFriends());
        totalFriends.addAll(newFriends);

        user.setFriends(totalFriends);
        userRepo.upsertUser(user);
    }

    @Override
    public void removeFriends(ChangeFriendsDto friends) {
        var user = doFindUser(friends.userId());
        var oldFriends = doFindUsers(friends.friendIds());

        var totalFriends = new HashSet<>(user.getFriends());
        totalFriends.removeAll(oldFriends);

        user.setFriends(totalFriends);
        userRepo.upsertUser(user);
    }

    private UserEntity doFindUser(UUID userId) {
        var user = userRepo.findUserById(userId);
        return user.orElseThrow(() -> new NoUserFoundException(userId));
    }

    private Set<UserEntity> doFindUsers(Set<UUID> userIds) {
        return userRepo.findUsersById(userIds).collect(Collectors.toSet());
    }
}
