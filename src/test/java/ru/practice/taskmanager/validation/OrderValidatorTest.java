package ru.practice.taskmanager.validation;

import org.junit.jupiter.api.Test;
import ru.practice.taskmanager.model.Order;
import ru.practice.taskmanager.model.OrderPriority;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderValidatorTest {
    private final OrderValidator validator = new OrderValidator();

    @Test
    void validateShouldAcceptCorrectOrder() {
        Order order = new Order("1", "Ivan", "Moscow", OrderPriority.REGULAR);

        assertDoesNotThrow(() -> validator.validate(order));
    }

    @Test
    void validateShouldRejectNullObject() {
        assertThrows(ValidationException.class, () -> validator.validate(null));
    }

    @Test
    void validateShouldRejectNullId() {
        Order order = new Order(null, "Ivan", "Moscow", OrderPriority.REGULAR);

        assertThrows(ValidationException.class, () -> validator.validate(order));
    }

    @Test
    void validateShouldRejectBlankId() {
        Order order = new Order(" ", "Ivan", "Moscow", OrderPriority.REGULAR);

        assertThrows(ValidationException.class, () -> validator.validate(order));
    }

    @Test
    void validateShouldRejectNullCustomerName() {
        Order order = new Order("1", null, "Moscow", OrderPriority.REGULAR);

        assertThrows(ValidationException.class, () -> validator.validate(order));
    }

    @Test
    void validateShouldRejectBlankCustomerName() {
        Order order = new Order("1", " ", "Moscow", OrderPriority.REGULAR);

        assertThrows(ValidationException.class, () -> validator.validate(order));
    }

    @Test
    void validateShouldRejectNullAddress() {
        Order order = new Order("1", "Ivan", null, OrderPriority.REGULAR);

        assertThrows(ValidationException.class, () -> validator.validate(order));
    }

    @Test
    void validateShouldRejectBlankAddress() {
        Order order = new Order("1", "Ivan", " ", OrderPriority.REGULAR);

        assertThrows(ValidationException.class, () -> validator.validate(order));
    }

    @Test
    void validateShouldRejectNullPriority() {
        Order order = new Order("1", "Ivan", "Moscow", null);

        assertThrows(ValidationException.class, () -> validator.validate(order));
    }
}