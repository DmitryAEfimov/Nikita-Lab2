package ru.nikita.lab2.application.utils;

import tools.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> T deserialize(String value, Class<T> targetClass) {
        return MAPPER.readValue(value, targetClass);
    }

    public static <T> String serialize(T obj) {
       return MAPPER.writeValueAsString(obj);
    }
}
