package com.live;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		LiveOrderBoard liveOrderBoard = new LiveOrderBoard();

		liveOrderBoard.registerOrder(new Order("user1", 3.5, 306, OrderType.SELL));
		liveOrderBoard.registerOrder(new Order("user2", 1.2, 310, OrderType.SELL));
		liveOrderBoard.registerOrder(new Order("user3", 1.5, 307, OrderType.SELL));
		liveOrderBoard.registerOrder(new Order("user4", 2.0, 306, OrderType.SELL));

		List<Order> summary = liveOrderBoard.getSummary();

		System.out.println("Live Order Board Summary:");
		for (Order order : summary) {
			System.out.println(order.getQuantity() + " kg for £" + order.getPricePerKg() + " // " + order.getUserId());
		}
	}
}
