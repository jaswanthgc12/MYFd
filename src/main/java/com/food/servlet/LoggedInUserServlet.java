package com.food.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.User;
import com.food.dao.UserDAO;
import com.food.daoimpl.UserDAOImpl;

@WebServlet("/login")
public class LoggedInUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {
            User user = userDAO.getUserByEmail(email);

            if (user != null && user.getHashedpassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", user);
                response.sendRedirect(request.getContextPath() + "/home.jsp");
            } else {
                // Invalid credentials
                response.sendRedirect(request.getContextPath() + "/login.jsp?error=invalidCredentials");
            }
        } else {
            // Missing email or password
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=missingData");
        }
    }
}
