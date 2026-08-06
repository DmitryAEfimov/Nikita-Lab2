package ru.nikita.lab2.service;

import ru.nikita.lab2.api.dto.FriendDto;

public interface FriendService {
    void addFriend(FriendDto friends);
    void removeFriend(FriendDto friends);
}
