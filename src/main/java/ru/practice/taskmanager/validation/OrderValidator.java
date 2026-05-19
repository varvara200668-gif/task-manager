package ru.practice.taskmanager.validation;

import ru.practice.taskmanager.annotation.NotNull;
import ru.practice.taskmanager.annotation.OrderType;
import ru.practice.taskmanager.model.OrderPriority;

import java.lang.reflect.Field;

public class OrderValidator {
    public void validate(Object object) {
        if (object == null) {
            throw new ValidationException("Object must not be null");
        }

        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            checkField(object, field);
        }
    }

    private void checkField(Object object, Field field) {
        if (field.isAnnotationPresent(NotNull.class)) {
            Object value = getValue(object, field);

            if (value == null) {
                throw new ValidationException("Field " + field.getName() + " must not be null");
            }

            if (value instanceof String text && text.isBlank()) {
                throw new ValidationException("Field " + field.getName() + " must not be blank");
            }
        }

        if (field.isAnnotationPresent(OrderType.class)) {
            Object value = getValue(object, field);

            if (!(value instanceof OrderPriority)) {
                throw new ValidationException("Field " + field.getName() + " must contain order type");
            }
        }
    }

    private Object getValue(Object object, Field field) {
        try {
            field.setAccessible(true);
            return field.get(object);
        } catch (IllegalAccessException exception) {
            throw new ValidationException("Cannot read field: " + field.getName());
        }
    }
}