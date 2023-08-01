package com.live;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		LiveOrderBoard orderBoard = new LiveOrderBoard();

		Order order1 = new Order("user1", 3.5, 306, OrderType.SELL);
		Order order2 = new Order("user2", 1.2, 310, OrderType.SELL);
		Order order3 = new Order("user3", 1.5, 307, OrderType.SELL);
		Order order4 = new Order("user4", 2.0, 306, OrderType.SELL);

		orderBoard.registerOrder(order1);
		orderBoard.registerOrder(order2);
		orderBoard.registerOrder(order3);
		orderBoard.registerOrder(order4);

		List<String> summaryInformation = orderBoard.getSummaryInformation();

		for (String summary : summaryInformation) {
			System.out.println(summary);
		}
	}
}
