package com.food.daoimpl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.food.Order;
import com.food.dao.OrderDAO;
import com.food.util.DBConnectionUtil;

public class OrderDAOImpl implements OrderDAO{

	@Override
	public int addOrder(Order order) {
	    int generatedOrderId = 0; // Initialize with a default value
	    String sql = "INSERT INTO ordertable(userId, RestaurantId, OrderDate, TotalAmount, Status, PaymentMethod) " +
	                 "VALUES (?, ?, ?, ?, ?, ?)";
	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement pre = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) { // Specify RETURN_GENERATED_KEYS

	        pre.setInt(1, order.getUserId());
	        pre.setInt(2, order.getRestaurentId());
	        pre.setTimestamp(3, order.getOrderDate());
	        pre.setDouble(4, order.getTotalAmount());
	        pre.setString(5, order.getStatus());
	        pre.setString(6, order.getPaymentMethod());
	        pre.executeUpdate();

	        ResultSet rs = pre.getGeneratedKeys();
	        if (rs.next()) {
	            generatedOrderId = rs.getInt(1);
	            order.setOrderId(generatedOrderId); // Set the generated order ID in the Order object
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return generatedOrderId; // Return the generated order ID
	}


	@Override
	public Order getOrder(int orderId) {
		String Sql = "SELECT * FROM ordertable WHERE OrderID=?";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(Sql)) {
            pre.setInt(1, orderId);
            ResultSet res = pre.executeQuery();
            if (res.next()) {
                int userId = res.getInt("userId");
                int restaurantId = res.getInt("RestaurantId");
                Timestamp orderDate = res.getTimestamp("OrderDate");
                Double totalAmount = res.getDouble("TotalAmount");
                String status = res.getString("Status");
                String paymentMethod = res.getString("PaymentMethod");
                return new Order(orderId, userId, restaurantId, orderDate, totalAmount, status, paymentMethod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public void updateOrder(Order order) {
		String sql = "UPDATE ordertable SET userId=?, RestaurantId=?, OrderDate=?, TotalAmount=?, Status=?, PaymentMethod=? WHERE OrderID=?";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setInt(1, order.getUserId());
            pre.setInt(2, order.getRestaurentId());
            pre.setTimestamp(3, order.getOrderDate());
            pre.setDouble(4, order.getTotalAmount());
            pre.setString(5, order.getStatus());
            pre.setString(6, order.getPaymentMethod());
            pre.setInt(7, order.getOrderId());
            pre.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void deleteOrder(int orderId) {
		String sql = "DELETE FROM ordertable WHERE OrderID=?";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setInt(1, orderId);
            pre.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public List<Order> getAllOrderByUser(int userId) {
		List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM ordertable";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql);
             ResultSet res = pre.executeQuery()) {

            while (res.next()) {
                int orderId = res.getInt("OrderID");
                int userId1 = res.getInt("userId");
                int restaurantId = res.getInt("RestaurantId");
                Timestamp orderDate = res.getTimestamp("OrderDate");
                Double totalAmount = res.getDouble("TotalAmount");
                String status = res.getString("Status");
                String paymentMethod = res.getString("PaymentMethod");
                orderList.add(new Order(orderId, userId1, restaurantId, orderDate, totalAmount, status, paymentMethod));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
	}

}
