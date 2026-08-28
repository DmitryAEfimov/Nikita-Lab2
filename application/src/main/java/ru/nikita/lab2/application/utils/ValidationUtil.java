package ru.nikita.lab2.application.utils;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Comparator;
import java.util.stream.Collectors;

public final class ValidationUtil {
    private static Validator VALIDATOR;

    public static String validate(Object obj) {
        var violations = getValidator().validate(obj);
        if (!violations.isEmpty()) {
            return violations.stream()
                    .sorted(Comparator.comparing(cv -> cv.getPropertyPath().toString()))
                    .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                    .collect(Collectors.joining(System.lineSeparator()));
        }

        return null;
    }

    private synchronized static Validator getValidator() {
        if (VALIDATOR == null) {
            try (var vf = Validation.buildDefaultValidatorFactory()) {
                VALIDATOR = vf.getValidator();
            }
        }

        return VALIDATOR;
    }
}
