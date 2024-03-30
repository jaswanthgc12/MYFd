package com.food;

public class User {
    private int userId;
    private String userName;
    private String Hashedpassword; // Renamed to follow conventions
    private String email;
    private long phoneNumber;
    private String address;
    private String Role; // Renamed to follow conventions

    // Default constructor
    public User() {
        
    }

    // Constructor with parameters
    public User(int userId, String userName, String Hashedpassword, String email,long phoneNumber, String address, String Role) {
        this.userId = userId;
        this.userName = userName;
        this.Hashedpassword = Hashedpassword;
        this.email = email;
        this.phoneNumber=phoneNumber;
        this.address = address;
        this.Role = Role;
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHashedpassword() {
        return Hashedpassword;
    }

    public void setHashedpassword(String Hashedpassword) {
        this.Hashedpassword = Hashedpassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
