package ru.nikita.lab2.api.dto;

import java.util.Set;
import java.util.UUID;

public record ChangeFriendsDto(UUID id, Set<UUID> friendIds) {
}
