package ru.practice.taskmanager.service;

import org.junit.jupiter.api.Test;
import ru.practice.taskmanager.model.Order;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class OrderManagerTest {
    @Test
    void startShouldProcessAllCreatedOrders() {
        OrderManager orderManager = new OrderManager();

        Map<String, Order> processedOrders = orderManager.start(6, 2);

        assertEquals(6, processedOrders.size());
        assertFalse(processedOrders.containsKey("POISON"));
    }
}