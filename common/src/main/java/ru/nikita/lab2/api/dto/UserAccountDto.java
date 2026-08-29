package ru.nikita.lab2.api.dto;

import java.util.UUID;

public record UserAccountDto(UUID accountId, String accountNumber) {
}
