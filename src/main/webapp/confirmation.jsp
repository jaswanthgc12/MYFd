<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.food.Order" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order Confirmation</title>
</head>
<body>
    <h1>Order Confirmation</h1>

    <% Order order = (Order) session.getAttribute("order"); %>

    <% if (order != null) { %>
        <div class="order-details">
            <p>Thank you for your order!</p>
            <p>Order ID: <%= order.getOrderId() %></p>
            <p>Total Amount: $<%= String.format("%.2f", order.getTotalAmount()) %></p>
            <p>Status: <%= order.getStatus() %></p>
            <p>Payment Method: <%= order.getPaymentMethod() %></p>
            <p>Order Date: <%= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getOrderDate()) %></p>
        </div>
        <p><a href="home.jsp">Back to Home</a></p>
        <p><a href="orderHistory.jsp">View Order History</a></p>
    <% } else { %>
        <p>Error: Order details not found.</p>
    <% } %>
</body>
</html>
