package ru.practice.taskmanager;

import ru.practice.taskmanager.model.Order;
import ru.practice.taskmanager.service.OrderManager;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int orderCount = 10;
        int consumerCount = 2;

        OrderManager orderManager = new OrderManager();

        Map<String, Order> processedOrders = orderManager.start(orderCount, consumerCount);

        System.out.println("Total processed orders: " + processedOrders.size());
    }
}