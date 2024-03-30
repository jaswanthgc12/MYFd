<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.food.CartItem" %>
<%@ page import="com.food.Cart" %>

<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart</title>
    <style>
        /* Add your CSS styles here */
        .cart-item {
            border: 1px solid #ccc;
            margin: 10px;
            padding: 10px;
            width: 300px;
        }
        .cart-item h3 {
            margin-top: 0;
        }
        .cart-item p {
            margin-bottom: 5px;
        }
        .quantity-input {
            width: 50px;
        }
        .update-btn {
            margin-top: 5px;
        }
        .remove-btn {
            margin-top: 5px;
            background-color: #ff6666;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
        }
        .add-more-items-btn {
            margin-top: 10px;
            display: inline-block;
            text-decoration: none;
            background-color: #4CAF50;
            color: white;
            padding: 8px 16px;
            border-radius: 5px;
        }
        .proceed-to-checkout-btn {
            margin-top: 10px;
            display: inline-block;
            text-decoration: none;
            background-color: #008CBA;
            color: white;
            padding: 8px 16px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <h2>Shopping Cart</h2>
    <div id="cartItems">
        <%
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart != null && !cart.getItems().isEmpty()) {
                for (CartItem item : cart.getItems().values()) {
        %>
                    <div class="cart-item">
                        <h3><%= item.getName() %></h3>
                        <p>Price: <%= item.getPrice() %></p>
                        <form action="Cart" method="post">
                            <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                            Quantity: <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1" class="quantity-input">
                            <input type="submit" value="Update" class="update-btn">
                            <input type="hidden" name="action" value="update">
                        </form>
                        <form action="Cart" method="post">
                            <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                            <input type="submit" value="Remove" class="remove-btn">
                            <input type="hidden" name="action" value="remove">
                        </form>
                    </div>
        <%
                }
            } else {
        %>
                <p>No items in the cart.</p>
        <%
            }
        %>
        
        <a href="Menu?restaurentId=<%= session.getAttribute("restaurentId") %>" class="add-more-items-btn"> Add More Items</a>
        
        <%
            if (session.getAttribute("cart") != null) {
        %>
            <form action="checkout.jsp" method="post">
            	<input type="submit" value = "proceed to checkout"
            	       class="proceed-to-checkout-btn">
            
            </form>
        <%
            }
        %>
    </div>
</body>
</html>
