package com.food.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.food.Menu;
import com.food.dao.MenuDAO;
import com.food.util.DBConnectionUtil;

public class MenuDAOImpl implements MenuDAO {

    @Override
    public void addMenu(Menu menu) {
        String sql = "INSERT INTO menu(RestaurantId, itemName, Description, Price, isAvailable) " +
                    "VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, menu.getRestaurentId());
            pre.setString(2, menu.getItemName());
            pre.setString(3, menu.getDiscription());
            pre.setDouble(4, menu.getPrice());
            pre.setBoolean(5, menu.isAvailable());
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Menu getMenu(int menuId) {
        String Sql = "SELECT * FROM menu WHERE menuID=?";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(Sql)) {
            pre.setInt(1, menuId);
            ResultSet res = pre.executeQuery();
            if (res.next()) {
                int restaurantId = res.getInt("RestaurantId");
                String itemName = res.getString("itemName");
                String description = res.getString("Description");
                double price = res.getDouble("Price");
                boolean isAvailable = res.getBoolean("isAvailable");
                return new Menu(menuId, restaurantId, itemName, description, price, isAvailable);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateMenu(Menu menu) {
        String sql = "UPDATE menu SET RestaurantId=?, itemName=?, Description=?, Price=?, isAvailable=? WHERE menuID=?";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, menu.getRestaurentId());
            pre.setString(2, menu.getItemName());
            pre.setString(3, menu.getDiscription());
            pre.setDouble(4, menu.getPrice());
            pre.setBoolean(5, menu.isAvailable());
            pre.setInt(6, menu.getMenuId());
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMenu(int menuId) {
        String sql = "DELETE FROM menu WHERE menuID=?";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, menuId);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Menu> getAllMenuByRestaurant(int restaurentId) {
        List<Menu> menuList = new ArrayList<>();
        String sql = "SELECT * FROM menu WHERE RestaurantId=?";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, restaurentId);
            ResultSet res = pre.executeQuery();
            while (res.next()) {
                int menuId = res.getInt("menuID");
                String itemName = res.getString("itemName");
                String description = res.getString("Description");
                double price = res.getDouble("Price");
                boolean isAvailable = res.getBoolean("isAvailable");
                menuList.add(new Menu(menuId, restaurentId, itemName, description, price, isAvailable));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }
}
