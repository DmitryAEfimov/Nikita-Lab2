package ru.nikita.lab2.service.impl;

import ru.nikita.lab2.api.dto.FriendDto;
import ru.nikita.lab2.dao.entity.UserEntity;
import ru.nikita.lab2.dao.repository.UserRepository;
import ru.nikita.lab2.service.FriendService;
import ru.nikita.lab2.service.exception.NoUserFoundException;

import java.util.HashSet;
import java.util.UUID;

public class FriendServiceImpl implements FriendService {
    private final UserRepository userRepo;

    public FriendServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void addFriend(FriendDto friends) {
        var user = doFindUser(friends.userId());
        var friend = doFindUser(friends.friendId());

        var newFriends = new HashSet<>(user.getFriends());
        newFriends.add(friend);
        // Равнозначно, но создает иммутабельный Set и экономит память
        // var newFriends = Stream.concat(user.getFriends().stream(), Stream.of(friend)).collect(Collectors.toSet());

        user.setFriends(newFriends);
        userRepo.upsertUser(user);
    }

    @Override
    public void removeFriend(FriendDto friends) {
        var user = doFindUser(friends.userId());
        var friend = doFindUser(friends.friendId());

        var newFriends = new HashSet<>(user.getFriends());
        newFriends.remove(friend);

        user.setFriends(newFriends);
        userRepo.upsertUser(user);
    }

    private UserEntity doFindUser(UUID userId) {
        var user = userRepo.findUserById(userId);
        return user.orElseThrow(() -> new NoUserFoundException(userId));
    }
}
