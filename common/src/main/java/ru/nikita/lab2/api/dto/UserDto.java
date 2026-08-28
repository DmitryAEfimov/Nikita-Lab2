package ru.nikita.lab2.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import ru.nikita.lab2.api.enumeration.Gender;
import ru.nikita.lab2.api.enumeration.HairColor;

import java.util.UUID;

public record UserDto(UUID userId,
                      @NotBlank(message = "Login is mandatory") @Min(value = 6, message = "Login should be at least 6 symbols") String login,
                      @NotBlank(message = "Name is mandatory") @Min(value = 2, message = "Name should be at least 2 symbols") @Max(value = 50, message = "Name should not be more then 50 symbols") String name,
                      @Positive(message = "Age should be positive value") @Size(min = 6, max = 150, message = "Age should be between 6 and 150 inclusive") int age,
                      Gender gender, HairColor hairColor) {
}
