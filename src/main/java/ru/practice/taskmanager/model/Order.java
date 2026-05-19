package ru.practice.taskmanager.model;

import ru.practice.taskmanager.annotation.NotNull;
import ru.practice.taskmanager.annotation.OrderType;

public class Order {
    private static final String POISON_ID = "POISON";

    @NotNull
    private final String id;

    @NotNull
    private final String customerName;

    @NotNull
    private final String address;

    @OrderType
    @NotNull
    private final OrderPriority priority;

    public Order(String id, String customerName, String address, OrderPriority priority) {
        this.id = id;
        this.customerName = customerName;
        this.address = address;
        this.priority = priority;
    }

    public static Order poisonPill() {
        return new Order(POISON_ID, "system", "system", OrderPriority.REGULAR);
    }

    public boolean isPoisonPill() {
        return POISON_ID.equals(id);
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public OrderPriority getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customerName='" + customerName + '\'' +
                ", address='" + address + '\'' +
                ", priority=" + priority +
                '}';
    }
}