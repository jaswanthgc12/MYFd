package com.food.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.OrderHistory;
import com.food.User;
import com.food.dao.OrderHistoryDAO;
import com.food.daoimpl.OrderHistoryDAOImpl;

@WebServlet("/orderHistory")
public class OrderHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderHistoryDAO orderHistoryDAO;

    public void init() {
        orderHistoryDAO = new OrderHistoryDAOImpl(); // Assuming you have implemented OrderHistoryDAOImpl
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");

        if (user != null) {
            List<OrderHistory> orderHistoryList = orderHistoryDAO.getOrderHistoriesByUser(user.getUserId());

            if (orderHistoryList != null && !orderHistoryList.isEmpty()) {
                session.setAttribute("orderHistory", orderHistoryList);
                response.sendRedirect(request.getContextPath() + "/orderHistory.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/orderHistory.jsp?error=noOrderHistory");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
