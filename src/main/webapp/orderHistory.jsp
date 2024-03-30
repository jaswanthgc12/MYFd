<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.food.OrderHistory" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html>
<head>
    <title>Order History</title>
    <style>
        /* Add your CSS styles here */
        .order {
            border: 1px solid #ccc;
            margin: 10px;
            padding: 10px;
            width: 300px;
        }
        .order h3 {
            margin-top: 0;
        }
        .order p {
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
    <h2>Order History</h2>
    <div id="orderHistoryList">
        <% 
            HttpSession sessionObj = request.getSession();
            List<OrderHistory> orderHistoryList = (List<OrderHistory>) sessionObj.getAttribute("orderHistory");
            if (orderHistoryList != null && !orderHistoryList.isEmpty()) {
                for (OrderHistory orderHistory : orderHistoryList) {
        %>
                    <div class="order">
                        <h3>Order ID: <%= orderHistory.getOrderId() %></h3>
                        <p>User ID: <%= orderHistory.getUserId() %></p>
                        <p>Order Date: <%= orderHistory.getOrderDate() %></p>
                        <p>Total Amount: $<%= orderHistory.getTotalAmount() %></p>
                        <p>Status: <%= orderHistory.getStatus() %></p>
                    </div>
        <% 
                }
            } else {
        %>
                <p>No order history available.</p>
        <% } %>
    </div>
</body>
</html>
