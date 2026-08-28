package ru.nikita.lab2.application.utils;

import tools.jackson.databind.ObjectMapper;

public final class JsonUtil {
    private static ObjectMapper MAPPER;

    public static <T> T deserialize(String value, Class<T> targetClass) {
        return getMapper().readValue(value, targetClass);
    }

    public static <T> String serialize(T obj) {
        return getMapper().writeValueAsString(obj);
    }

    private synchronized static ObjectMapper getMapper() {
        if (MAPPER == null) {
            MAPPER = new ObjectMapper();
        }

        return MAPPER;
    }
}
