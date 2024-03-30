package com.food.dao;

import java.util.List;

import com.food.OrderHistory;

public interface OrderHistoryDAO {
	void addOrderHistory(OrderHistory orderHistory);
	OrderHistory getOrderHistory(int orderHistoryId);
	void UpdateOrderHistory(OrderHistory orderHistory);
	void deleteOrderHistory(int orderHistoryId);
	List<OrderHistory> getOrderHistoriesByUser(int userId);
}
