<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.food.daoimpl.UserDAOImpl, com.food.User" %>
<html>
<head>
    <title>Sign Up Page</title>
</head>
<body>
    <h2>Sign Up</h2>
    
    <%
        String message = "";
        // Handling form submission for sign up
        if (request.getMethod().equalsIgnoreCase("post")) {
            try {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                String phoneNumberStr = request.getParameter("phoneNumber");
                long phoneNumber = Long.parseLong(phoneNumberStr);
                String address = request.getParameter("address");
                String role = "customer"; // Default role for new users
                
                User newUser = new User(1, username, password, email, phoneNumber, address, role); // Corrected parameter type
                
                // Adding the new user to the database
                UserDAOImpl userDAO = new UserDAOImpl();
                userDAO.addUser(newUser);
                
                message = "User registered successfully!";
                response.sendRedirect("login.jsp"); // Redirect to login page after successful registration
            } catch (NumberFormatException e) {
                message = "Invalid phone number. Please enter a valid phone number.";
            } catch (Exception e) {
                message = "An error occurred while processing your request. Please try again later.";
                e.printStackTrace();
            }
        }
    %>
    
    <form action="signUp.jsp" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        <label for="phoneNumber">Phone Number:</label>
        <input type="text" id="phoneNumber" name="phoneNumber" required><br><br>
        <label for="address">Address:</label>
        <textarea id="address" name="address" required></textarea><br><br>
        <input type="submit" value="Sign Up">
    </form>
</body>
</html>
