package ru.nikita.lab2.api.dto;

import jakarta.validation.constraints.Positive;
import ru.nikita.lab2.api.enumeration.OpType;

import java.util.UUID;

public record OperationDto(OpType opType, UUID fromAcc, UUID toAcc,
                           @Positive(message = "Operated amount should be positive value") double amount) {
}
