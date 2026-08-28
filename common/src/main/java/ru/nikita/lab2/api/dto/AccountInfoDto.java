package ru.nikita.lab2.api.dto;

import jakarta.validation.constraints.PositiveOrZero;

public record AccountInfoDto(AccountDto account,
                             @PositiveOrZero(message = "Account balance should not be negative") double currentBalance) {
}
