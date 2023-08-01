package com.live;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LiveOrderBoard {
    private List<Order> orders;

    public LiveOrderBoard() {
        this.orders = new ArrayList<>();
    }

	public synchronized void registerOrder(Order order) {
        orders.add(order);
    }

    public synchronized void cancelOrder(Order order) {
        orders.remove(order);
    }

    public synchronized List<Order> getSummary() {
        Comparator<Order> orderByPrice = Comparator.comparingDouble(Order::getPricePerKg);
        if (orders.isEmpty()) {
            return Collections.emptyList();
        }

        List<Order> sortedOrders = orders.stream()
                .sorted(orderByPrice.thenComparing(Order::getOrderType))
                .collect(Collectors.toList());

        List<Order> summaryOrders = new ArrayList<>();
        Order currentOrder = sortedOrders.get(0);
        double totalQuantity = currentOrder.getQuantity();

        for (int i = 1; i < sortedOrders.size(); i++) {
            Order nextOrder = sortedOrders.get(i);
            if (currentOrder.getPricePerKg() == nextOrder.getPricePerKg()) {
                totalQuantity += nextOrder.getQuantity();
            } else {
                summaryOrders.add(new Order(currentOrder.getUserId(), totalQuantity, currentOrder.getPricePerKg(), currentOrder.getOrderType()));
                currentOrder = nextOrder;
                totalQuantity = currentOrder.getQuantity();
            }
        }

        summaryOrders.add(new Order(currentOrder.getUserId(), totalQuantity, currentOrder.getPricePerKg(), currentOrder.getOrderType()));

        return summaryOrders;
    }
}
