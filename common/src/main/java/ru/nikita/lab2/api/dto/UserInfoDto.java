package ru.nikita.lab2.api.dto;

import ru.nikita.lab2.api.enumeration.Gender;
import ru.nikita.lab2.api.enumeration.HairColor;

import java.util.Set;
import java.util.UUID;

public record UserInfoDto(UUID id, String login, String name, int age, Gender gender, HairColor hairColor, Set<UserAccountDto> accounts, Set<UserFriendDto> friends) {
}
