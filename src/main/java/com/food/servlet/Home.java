package com.food.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.food.Restaurant;
import com.food.User;
import com.food.dao.RestaurentDAO;
import com.food.daoimpl.RestaurantDAOImpl;

@WebServlet("/Home")
public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Home() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        if (loggedInUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Retrieve restaurants from the DAO
        RestaurentDAO restaurantDAO = new RestaurantDAOImpl();
        List<Restaurant> restaurants = restaurantDAO.getAllRestaurants();

        // Set restaurants attribute and forward to JSP
        request.setAttribute("restaurants", restaurants);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
