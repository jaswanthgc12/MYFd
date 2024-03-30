<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.food.User" %>
<%@ page import="com.food.daoimpl.UserDAOImpl" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <h2>Login</h2>
    
    <%
        // Check if the user is already logged in
        User currentUser = (User) session.getAttribute("loggedInUser");
        if (currentUser != null) {
            response.sendRedirect("welcome.jsp");
        }
        
        // Handling form submission for login
        if (request.getMethod().equalsIgnoreCase("post")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            UserDAOImpl userDAO = new UserDAOImpl();
            User user = userDAO.getUserByEmail(email);
            
            if (user != null && user.getHashedpassword().equals(password)) {
                // Authentication successful
                session.setAttribute("loggedInUser", user);
                response.sendRedirect("home.jsp");
            } else {
                // Authentication failed
                out.println("<p style='color:red;'>Invalid email or password</p>");
            }
        }
    %>
    
    <form action="login" method="post">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>
