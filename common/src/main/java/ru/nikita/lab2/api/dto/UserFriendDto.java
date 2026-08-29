package ru.nikita.lab2.api.dto;

import java.util.UUID;

public record UserFriendDto(UUID friendId, String friendLogin) {
}
