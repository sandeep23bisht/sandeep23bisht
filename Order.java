package com.live;

import java.util.Objects;

class Order {
	private String userId;
	private double quantity;
	private double pricePerKg;
	private OrderType orderType;

	public Order(String userId, double quantity, double pricePerKg, OrderType orderType) {
		this.userId = userId;
		this.quantity = quantity;
		this.pricePerKg = pricePerKg;
		this.orderType = orderType;
	}

	public String getUserId() {
		return userId;
	}

	public double getQuantity() {
		return quantity;
	}

	public double getPricePerKg() {
		return pricePerKg;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Order order = (Order) o;
		return Double.compare(order.quantity, quantity) == 0 && Double.compare(order.pricePerKg, pricePerKg) == 0
				&& userId.equals(order.userId) && orderType == order.orderType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, quantity, pricePerKg, orderType);
	}

	@Override
	public String toString() {
		return String.format("%s: %.1f kg for £%.2f", orderType, quantity, pricePerKg);
	}
}
