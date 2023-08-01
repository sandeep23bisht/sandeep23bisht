package com.live;

import java.util.ArrayList;
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

    public synchronized List<String> getSummaryInformation() {
        List<Order> sortedOrders = orders.stream()
                .sorted(Comparator.comparingDouble(order -> order.getOrderType() == OrderType.BUY ? order.getPricePerKg() : -order.getPricePerKg()))
                .collect(Collectors.toList());

        List<String> summaryInformation = new ArrayList<>();
        int i = 0;
        while (i < sortedOrders.size()) {
            double totalPrice = sortedOrders.get(i).getPricePerKg();
            double totalQuantity = 0;

            for (int j = i; j < sortedOrders.size(); j++) {
                Order currentOrder = sortedOrders.get(j);
                if (currentOrder.getPricePerKg() == totalPrice) {
                    totalQuantity += currentOrder.getQuantity();
                } else {
                    break;
                }
                i = j;
            }

            summaryInformation.add(totalQuantity + " kg for £" + totalPrice);
            i++;
        }

        return summaryInformation;
    }

}
