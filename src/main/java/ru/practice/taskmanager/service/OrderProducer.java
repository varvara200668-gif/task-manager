package ru.practice.taskmanager.service;

import ru.practice.taskmanager.model.Order;
import ru.practice.taskmanager.model.OrderPriority;

import java.util.concurrent.BlockingQueue;

public class OrderProducer implements Runnable {
    private final BlockingQueue<Order> queue;
    private final int orderCount;
    private final int consumerCount;

    public OrderProducer(BlockingQueue<Order> queue, int orderCount, int consumerCount) {
        this.queue = queue;
        this.orderCount = orderCount;
        this.consumerCount = consumerCount;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= orderCount; i++) {
                OrderPriority priority;

                if (i % 3 == 0) {
                    priority = OrderPriority.URGENT;
                } else {
                    priority = OrderPriority.REGULAR;
                }

                Order order = new Order(
                        "ORD-" + i,
                        "Customer " + i,
                        "Address " + i,
                        priority
                );

                queue.put(order);
                System.out.println("Created: " + order);
            }

            for (int i = 0; i < consumerCount; i++) {
                queue.put(Order.poisonPill());
            }

        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}