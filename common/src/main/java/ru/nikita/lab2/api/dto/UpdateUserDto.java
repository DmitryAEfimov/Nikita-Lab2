package ru.nikita.lab2.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import ru.nikita.lab2.api.enumeration.Gender;
import ru.nikita.lab2.api.enumeration.HairColor;

import java.util.UUID;

public record UpdateUserDto(@NotNull UUID id,
                            @NotBlank @Size(min = 2, max = 50, message = "Name should be between 2 and 50 symbols") String name,
                            @Positive(message = "Age should be positive value") @Range(min = 6, max = 150, message = "Age should be between 6 and 150 inclusive") int age,
                            Gender gender, HairColor hairColor) {
}
