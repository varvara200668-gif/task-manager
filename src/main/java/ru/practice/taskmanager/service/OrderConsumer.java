package ru.practice.taskmanager.service;

import ru.practice.taskmanager.model.Order;
import ru.practice.taskmanager.validation.OrderValidator;
import ru.practice.taskmanager.validation.ValidationException;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class OrderConsumer implements Runnable {
    private final BlockingQueue<Order> queue;
    private final Map<String, Order> processedOrders;
    private final OrderValidator validator;

    public OrderConsumer(BlockingQueue<Order> queue,
                         Map<String, Order> processedOrders,
                         OrderValidator validator) {
        this.queue = queue;
        this.processedOrders = processedOrders;
        this.validator = validator;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Order order = queue.take();

                if (order.isPoisonPill()) {
                    break;
                }

                try {
                    validator.validate(order);

                    Thread.sleep(100);

                    processedOrders.put(order.getId(), order);

                    System.out.println(Thread.currentThread().getName()
                            + " processed: " + order);

                } catch (ValidationException exception) {
                    System.out.println("Invalid order skipped: " + exception.getMessage());
                }
            }
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}