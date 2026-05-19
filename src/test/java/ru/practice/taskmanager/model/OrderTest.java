package ru.practice.taskmanager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void orderShouldReturnAllFields() {
        Order order = new Order("1", "Ivan", "Moscow", OrderPriority.URGENT);

        assertEquals("1", order.getId());
        assertEquals("Ivan", order.getCustomerName());
        assertEquals("Moscow", order.getAddress());
        assertEquals(OrderPriority.URGENT, order.getPriority());
    }

    @Test
    void poisonPillShouldBeDetected() {
        Order order = Order.poisonPill();

        assertTrue(order.isPoisonPill());
        assertEquals("POISON", order.getId());
    }

    @Test
    void normalOrderShouldNotBePoisonPill() {
        Order order = new Order("1", "Ivan", "Moscow", OrderPriority.REGULAR);

        assertFalse(order.isPoisonPill());
    }

    @Test
    void toStringShouldContainOrderData() {
        Order order = new Order("15", "Petr", "Tula", OrderPriority.REGULAR);

        String text = order.toString();

        assertTrue(text.contains("15"));
        assertTrue(text.contains("Petr"));
        assertTrue(text.contains("Tula"));
        assertTrue(text.contains("REGULAR"));
    }
}