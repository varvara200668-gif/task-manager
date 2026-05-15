package ru.practice.taskmanager.service;

import org.junit.jupiter.api.Test;
import ru.practice.taskmanager.model.Order;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderProducerTest {
    @Test
    void runShouldCreateOrdersAndPoisonPills() {
        BlockingQueue<Order> queue = new LinkedBlockingQueue<>();

        OrderProducer producer = new OrderProducer(queue, 5, 2);
        producer.run();

        long poisonCount = queue.stream()
                .filter(Order::isPoisonPill)
                .count();

        assertEquals(7, queue.size());
        assertEquals(2, poisonCount);
    }
}