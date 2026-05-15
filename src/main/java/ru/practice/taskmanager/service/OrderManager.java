package ru.practice.taskmanager.service;

import ru.practice.taskmanager.model.Order;
import ru.practice.taskmanager.validation.OrderValidator;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class OrderManager {
    private final BlockingQueue<Order> queue = new LinkedBlockingQueue<>();
    private final Map<String, Order> processedOrders = new ConcurrentHashMap<>();
    private final OrderValidator validator = new OrderValidator();

    public Map<String, Order> start(int orderCount, int consumerCount) {
        ExecutorService executorService = Executors.newFixedThreadPool(consumerCount + 1);

        executorService.submit(new OrderProducer(queue, orderCount, consumerCount));

        for (int i = 0; i < consumerCount; i++) {
            executorService.submit(new OrderConsumer(queue, processedOrders, validator));
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException exception) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

        return processedOrders;
    }
}