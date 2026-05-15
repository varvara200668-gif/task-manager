package ru.practice.taskmanager.service;

import org.junit.jupiter.api.Test;
import ru.practice.taskmanager.model.Order;
import ru.practice.taskmanager.model.OrderPriority;
import ru.practice.taskmanager.validation.OrderValidator;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderConsumerTest {
    @Test
    void runShouldProcessOnlyValidOrders() throws InterruptedException {
        BlockingQueue<Order> queue = new LinkedBlockingQueue<>();
        Map<String, Order> processedOrders = new ConcurrentHashMap<>();

        Order validOrder = new Order("1", "Ivan", "Moscow", OrderPriority.URGENT);
        Order invalidOrder = new Order(null, "Petr", "Tula", OrderPriority.REGULAR);

        queue.put(validOrder);
        queue.put(invalidOrder);
        queue.put(Order.poisonPill());

        OrderConsumer consumer = new OrderConsumer(queue, processedOrders, new OrderValidator());
        consumer.run();

        assertEquals(1, processedOrders.size());
        assertTrue(processedOrders.containsKey("1"));
    }
}