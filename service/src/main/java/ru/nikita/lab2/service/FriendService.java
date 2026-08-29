package ru.nikita.lab2.service;

import ru.nikita.lab2.api.dto.ChangeFriendsDto;

public interface FriendService {
    void addFriends(ChangeFriendsDto friends);
    void removeFriends(ChangeFriendsDto friends);
}
