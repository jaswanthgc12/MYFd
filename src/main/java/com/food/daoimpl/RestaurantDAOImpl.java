package com.food.daoimpl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.food.Restaurant;
import com.food.dao.RestaurentDAO;
import com.food.util.DBConnectionUtil;

public class RestaurantDAOImpl implements RestaurentDAO {

    @Override
    public void addRestaurant(Restaurant restaurant) {
        String sql = "INSERT INTO restaurant(Name, CuisineType, DelivaryTime, Address, AdminuserID, rating, isActive, imagePath) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setString(1, restaurant.getName());
            pre.setString(2, restaurant.getCuisineType());
            pre.setInt(3, restaurant.getDeliveryTime());
            pre.setString(4, restaurant.getAddress());
            pre.setInt(5, restaurant.getAdminUserId());
            pre.setDouble(6, restaurant.getRating());
            pre.setBoolean(7, restaurant.isActive());

            // Convert byte[] to InputStream
            InputStream inputStream = new ByteArrayInputStream(restaurant.getImageData());
            pre.setBinaryStream(8, inputStream);

            pre.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restaurant getRestaurant(int restaurantId) {
        String sql = "SELECT * FROM restaurant WHERE RestaurantId=?";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, restaurantId);
            ResultSet res = pre.executeQuery();
            if (res.next()) {
                int id = res.getInt("RestaurantId");
                String name = res.getString("Name");
                String cuisineType = res.getString("CuisineType");
                int deliveryTime = res.getInt("DelivaryTime");
                String address = res.getString("Address");
                int adminUserId = res.getInt("AdminuserID");
                double rating = res.getDouble("rating");
                boolean isActive = res.getBoolean("isActive");
                
                // Get the image data as byte[]
                InputStream inputStream = res.getBinaryStream("imagePath");
                byte[] imageData = inputStream.readAllBytes();

                return new Restaurant(id, name, cuisineType, deliveryTime, address, adminUserId, rating, isActive, imageData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		String sql = "UPDATE restaurant SET Name=?, CuisineType=?, DelivaryTime=?, Address=?, AdminuserID=?, rating=?, isActive=?, imagePath=? WHERE RestaurantId=?";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setString(1, restaurant.getName());
            pre.setString(2, restaurant.getCuisineType());
            pre.setInt(3, restaurant.getDeliveryTime());
            pre.setString(4, restaurant.getAddress());
            pre.setInt(5, restaurant.getAdminUserId());
            pre.setDouble(6, restaurant.getRating());
            pre.setBoolean(7, restaurant.isActive());

            // Convert byte[] to InputStream
            InputStream inputStream = new ByteArrayInputStream(restaurant.getImageData());
            pre.setBinaryStream(8, inputStream);

            pre.setInt(9, restaurant.getRestaurantId());
            pre.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void deleteRestaurant(int restaurantId) {
		String sql = "DELETE FROM restaurant WHERE RestaurantId=?";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setInt(1, restaurantId);
            pre.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		List<Restaurant> restaurantList = new ArrayList<>();
        String sql = "SELECT * FROM restaurant";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql);
             ResultSet res = pre.executeQuery()) {

            while (res.next()) {
                int id = res.getInt("RestaurantId");
                String name = res.getString("Name");
                String cuisineType = res.getString("CuisineType");
                int deliveryTime = res.getInt("DelivaryTime");
                String address = res.getString("Address");
                int adminUserId = res.getInt("AdminuserID");
                double rating = res.getDouble("rating");
                boolean isActive = res.getBoolean("isActive");
                
                // Get the image data as byte[]
                InputStream inputStream = res.getBinaryStream("imagePath");
                byte[] imageData = inputStream.readAllBytes();

                restaurantList.add(new Restaurant(id, name, cuisineType, deliveryTime, address, adminUserId, rating, isActive, imageData));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurantList;
	}

    
}
