package com.food.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.food.User;
import com.food.dao.UserDAO;
import com.food.util.DBConnectionUtil;

public class UserDAOImpl implements UserDAO{

	@Override
	public void addUser(User user) {
		String sql = "INSERT INTO user(userName, Password, email, phoneNumber, address, role) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection con = DBConnectionUtil.getConnection();
		     PreparedStatement pre = con.prepareStatement(sql)) {

		    pre.setString(1, user.getUserName());
		    pre.setString(2, user.getHashedpassword());
		    pre.setString(3, user.getEmail());
		    pre.setLong(4, user.getPhoneNumber()); // Assuming phoneNumber is of type long
		    pre.setString(5, user.getAddress());
		    pre.setString(6, user.getRole());
		    pre.executeUpdate();

		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}

	@Override
	public User getUser(int userId) {
		String Sql = "SELECT * FROM user WHERE userId=? ";
		try(Connection con = DBConnectionUtil.getConnection();
			PreparedStatement pre = con.prepareStatement(Sql)){
			pre.setInt(1, userId);
			ResultSet res = pre.executeQuery();
			if(res.next()) {
				int id1 = res.getInt("userId");
				String name = res.getString("userName");
				String password=res.getString("password");
				String email = res.getString("email");
				long PhoneNumber = res.getLong("phoneNumber");
				String address = res.getString("address");
				String role = res.getString("role");
				return new User(id1, name, password, email, PhoneNumber, address, role);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateUser(User user) {
		String sql = "UPDATE user SET userName=?, password=?, email=?,phoneNumber=?, address=?, role=? WHERE userId=?";
        try(Connection con = DBConnectionUtil.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)) {
            
            pre.setString(1, user.getUserName());
            pre.setString(2, user.getHashedpassword());
            pre.setString(3, user.getEmail());
            pre.setLong(4,user.getPhoneNumber());
            pre.setString(5, user.getAddress());
            pre.setString(6, user.getRole());
            pre.setInt(7, user.getUserId());
            pre.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void deleteUser(int userId) {
		String sql = "DELETE FROM user WHERE userId=?";
        try(Connection con = DBConnectionUtil.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)) {
            
            pre.setInt(1, userId);
            pre.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try(Connection con = DBConnectionUtil.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)){
            ResultSet res = pre.executeQuery();
            while(res.next()) {
                int id = res.getInt("userId");
                String name = res.getString("userName");
                String password = res.getString("password");
                String email = res.getString("email");
                long phoneNumber = res.getLong("phoneNumber");
                String address = res.getString("address");
                String role = res.getString("role");
                userList.add(new User(id, name, password, email,phoneNumber, address, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
	}

	@Override
	public User getUserByUsername(String username) {
		User user = null;
        String sql = "SELECT * FROM user WHERE userName=?";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1, username);
            ResultSet res = pre.executeQuery();
            if (res.next()) {
                int userId = res.getInt("userId");
                String userName = res.getString("userName");
                String hashedpassword = res.getString("password");
                String email = res.getString("email");
                long phoneNumber = res.getLong("phoneNumber");
                String address = res.getString("address");
                String role = res.getString("role");
                user = new User(userId, userName, hashedpassword, email, phoneNumber, address, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
	}

	@Override
	public User getUserByEmail(String email) {
	    User user = null;
	    String sql = "SELECT * FROM user WHERE email=?";
	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement pre = con.prepareStatement(sql)) {
	        pre.setString(1, email);
	        ResultSet res = pre.executeQuery();
	        if (res.next()) {
	            int userId = res.getInt("userId");
	            String userName = res.getString("userName");
	            String hashedPassword = res.getString("password");
	            long phoneNumber = res.getLong("phoneNumber");
	            String address = res.getString("address");
	            String role = res.getString("role");
	            user = new User(userId, userName, hashedPassword, email, phoneNumber, address, role);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return user;
	}

}
