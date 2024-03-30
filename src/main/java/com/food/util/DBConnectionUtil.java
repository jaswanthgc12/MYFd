package com.food.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {
	 
	private static final String DATABAE_URL="jdbc:mysql://localhost:3306/fooddelivaryapp";
	private static final String DATABAE_USER="root";
	private static final String DATABAE_PASSWORD="jaswanth123";
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DATABAE_URL, DATABAE_USER, DATABAE_PASSWORD);
		} catch ( SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
