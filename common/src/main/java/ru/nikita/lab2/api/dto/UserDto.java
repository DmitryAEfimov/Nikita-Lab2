package ru.nikita.lab2.api.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import ru.nikita.lab2.api.enumeration.Gender;
import ru.nikita.lab2.api.enumeration.HairColor;

import java.util.UUID;

public record UserDto(UUID userId,
                      @NotBlank(message = "Login is mandatory") @Size(min = 6, message = "Login should be at least 6 symbols") String login,
                      @NotBlank(message = "Name is mandatory") @Size(min = 2, max = 50, message = "Name should be between 2 and 50 symbols") String name,
                      @Positive(message = "Age should be positive value") @Range(min = 6, max = 150, message = "Age should be between 6 and 150 inclusive") int age,
                      Gender gender, HairColor hairColor) {
}
