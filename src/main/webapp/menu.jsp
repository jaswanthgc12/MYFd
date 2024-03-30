<%@ page import="java.util.List" %>
<%@ page import="com.food.Menu" %>

<!DOCTYPE html>
<html>
<head>
    <title>Menu</title>
    <style>
        /* Add your CSS styles here */
        .menu-item {
            border: 1px solid #ccc;
            margin: 10px;
            padding: 10px;
            width: 300px;
            float: left;
        }
        .menu-item h3 {
            margin-top: 0;
        }
        .menu-item p {
            margin-bottom: 5px;
        }
        .quantity-input {
            width: 50px;
        }
        .add-to-cart-btn {
            margin-top: 5px;
        }
    </style>
</head>
<body>
    <h2>Menu</h2>
    <div id="menuItems">
        <%
            Object menuListObj = request.getAttribute("menuList");
            if (menuListObj != null && menuListObj instanceof List) {
                List<Menu> menuList = (List<Menu>) menuListObj;
                if (!menuList.isEmpty()) {
                    for (Menu menuItem : menuList) {
        %>
                        <div class="menu-item">
                            <h3><%= menuItem.getItemName() %></h3>
                            <p>Description: <%= menuItem.getDiscription() %></p>
                            <p>Price: $<%= menuItem.getPrice() %></p>
                            <form action="Cart" method="post">
                                <input type="hidden" name="itemId" value="<%= menuItem.getMenuId() %>">
                                Quantity: <input type="number" name="quantity" value="1" min="1" class="quantity-input">
                                <input type="submit" value="Add To Cart" class="add-to-cart-btn">
                                <input type="hidden" name="action" value="add">
                            </form>
                        </div>
        <%
                    }
                } else {
        %>
                    <p>No menu items available.</p>
        <%
                }
            } else {
        %>
                <p>No menu items available.</p>
        <%
            }
        %>
    </div>
</body>
</html>