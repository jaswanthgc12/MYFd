package com.food.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.food.OrderItem;
import com.food.dao.OrderItemDAO;
import com.food.util.DBConnectionUtil;

public class OrderItemDAOImpl implements OrderItemDAO {

    @Override
    public void addOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO orderitemtable(OrderId, menuId, Quantity, ItemTotalAmount) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setInt(1, orderItem.getOrderId());
            pre.setInt(2, orderItem.getMenuId());
            pre.setInt(3, orderItem.getQuantity());
            pre.setDouble(4, orderItem.getItemTotal());
            pre.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderItem getOrderItem(int orderItemId) {
    	String sql = "SELECT * FROM orderitemtable WHERE OrderItemID=?";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, orderItemId);
            ResultSet res = pre.executeQuery();
            if (res.next()) {
                int orderId = res.getInt("OrderId");
                int menuId = res.getInt("menuId");
                int quantity = res.getInt("Quantity");
                double itemTotal = res.getDouble("ItemTotalAmount");
                return new OrderItem(orderItemId, orderId, menuId, quantity, itemTotal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
    	String sql = "UPDATE orderitemtable SET OrderId=?, menuId=?, Quantity=?, ItemTotalAmount=? WHERE OrderItemID=?";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setInt(1, orderItem.getOrderId());
            pre.setInt(2, orderItem.getMenuId());
            pre.setInt(3, orderItem.getQuantity());
            pre.setDouble(4, orderItem.getItemTotal());
            pre.setInt(5, orderItem.getOrderItemId());
            pre.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
    	String sql = "DELETE FROM orderitemtable WHERE OrderItemID=?";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setInt(1, orderItemId);
            pre.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
    	List<OrderItem> orderItemList = new ArrayList<>();
        String sql = "SELECT * FROM orderitemtable";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql);
             ResultSet res = pre.executeQuery()) {

            while (res.next()) {
                int orderItemId = res.getInt("OrderItemID");
                int orderId = res.getInt("OrderId");
                int menuId = res.getInt("menuId");
                int quantity = res.getInt("Quantity");
                double itemTotal = res.getDouble("ItemTotalAmount");
                orderItemList.add(new OrderItem(orderItemId, orderId, menuId, quantity, itemTotal));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
    	List<OrderItem> orderItemList = new ArrayList<>();
        String sql = "SELECT * FROM orderitemtable WHERE OrderId=?";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setInt(1, orderId);
            ResultSet res = pre.executeQuery();
            while (res.next()) {
                int orderItemId = res.getInt("OrderItemID");
                int menuId = res.getInt("menuId");
                int quantity = res.getInt("Quantity");
                double itemTotal = res.getDouble("ItemTotalAmount");
                orderItemList.add(new OrderItem(orderItemId, orderId, menuId, quantity, itemTotal));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }
}
