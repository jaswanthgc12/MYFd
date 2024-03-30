package com.food.dao;

import java.util.List;

import com.food.Order;

public interface OrderDAO {
	int addOrder(Order order);
	Order getOrder(int orderId);
	void updateOrder(Order order);
	void deleteOrder(int orderId);
	List<Order> getAllOrderByUser(int userId); 
}
