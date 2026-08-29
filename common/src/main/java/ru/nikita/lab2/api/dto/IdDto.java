package ru.nikita.lab2.api.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record IdDto(@NotNull UUID id) {
}
