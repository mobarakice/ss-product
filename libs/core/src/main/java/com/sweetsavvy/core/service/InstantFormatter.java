package com.sweetsavvy.core.service;

import com.sweetsavvy.core.model.FormatInstant;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class InstantFormatter {

    public String format(Instant instant, FormatInstant formatInstant) {
        if (instant == null) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern(formatInstant.pattern())
                .withZone(ZoneId.of(formatInstant.zone()));

        return formatter.format(instant);
    }

    public void formatFields(Object object) {
        Class<?> clazz = object.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(FormatInstant.class)) {
                FormatInstant annotation = field.getAnnotation(FormatInstant.class);
                field.setAccessible(true);

                try {
                    Object value = field.get(object);
                    if (value instanceof Instant) {
                        String formattedValue = format((Instant) value, annotation);
                        field.set(object, formattedValue);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Unable to format Instant field: " + field.getName(), e);
                }
            }
        }
    }
}
