package com.food.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.food.OrderHistory;
import com.food.dao.OrderHistoryDAO;
import com.food.util.DBConnectionUtil;

public class OrderHistoryDAOImpl implements OrderHistoryDAO{

	@Override
	public void addOrderHistory(OrderHistory orderHistory) {
		String sql = "INSERT INTO orderhistory(userId, OrderId,OrderDate, TotalAmount, Status) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setInt(1, orderHistory.getUserId());
            pre.setInt(2, orderHistory.getOrderId());
            pre.setTimestamp(3, new java.sql.Timestamp(orderHistory.getOrderDate().getTime()));
            pre.setDouble(4, orderHistory.getTotalAmount());
            pre.setString(5, orderHistory.getStatus());
            pre.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public OrderHistory getOrderHistory(int orderHistoryId) {
		String sql = "SELECT * FROM orderhistory WHERE OrderHistoryID=?";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, orderHistoryId);
            ResultSet res = pre.executeQuery();
            if (res.next()) {
                int userId = res.getInt("userId");
                int orderId = res.getInt("OrderId");
                java.sql.Timestamp orderDate = res.getTimestamp("OrderDate");
                double totalAmount = res.getDouble("TotalAmount");
                String status = res.getString("Status");
                return new OrderHistory(orderHistoryId, userId, orderId, new java.util.Date(orderDate.getTime()), totalAmount, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public void UpdateOrderHistory(OrderHistory orderHistory) {
		String sql = "UPDATE orderhistory SET userId=?, OrderId=?, OrderDate=?, TotalAmount=?, Status=? WHERE OrderHistoryID=?";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setInt(1, orderHistory.getUserId());
            pre.setInt(2, orderHistory.getOrderId());
            pre.setTimestamp(3, new java.sql.Timestamp(orderHistory.getOrderDate().getTime()));
            pre.setDouble(4, orderHistory.getTotalAmount());
            pre.setString(5, orderHistory.getStatus());
            pre.setInt(6, orderHistory.getOrderHistoryId());
            pre.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void deleteOrderHistory(int orderHistoryId) {
		String sql = "DELETE FROM orderhistory WHERE OrderHistoryID=?";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setInt(1, orderHistoryId);
            pre.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public List<OrderHistory> getOrderHistoriesByUser(int userId) {
	    List<OrderHistory> orderHistoryList = new ArrayList<>();
	    String sql = "SELECT * FROM orderhistory WHERE userId=?";
	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement pre = con.prepareStatement(sql)) {

	        pre.setInt(1, userId);
	        try (ResultSet res = pre.executeQuery()) {
	            while (res.next()) {
	                int orderHistoryId = res.getInt("OrderHistoryID");
	                int orderId = res.getInt("OrderId");
	                java.sql.Timestamp orderDate = res.getTimestamp("OrderDate");
	                double totalAmount = res.getDouble("TotalAmount");
	                String status = res.getString("Status");
	                orderHistoryList.add(new OrderHistory(orderHistoryId, userId, orderId, new java.util.Date(orderDate.getTime()), totalAmount, status));
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return orderHistoryList;
	}


}
