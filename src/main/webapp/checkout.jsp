<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Checkout</title>
</head>
<body>
    <h2>Checkout</h2>
    <form action="checkout" method="post">
        <label for="address">Delivery Address:</label>
        <textarea id="address" name="address" required maxlength="255"></textarea><br><br>
        
        <label>Payment Method:</label>
        <select name="paymentMethod" required>
            <option value="UPI">UPI</option>
            <option value="Cash">Cash</option>
            <option value="Debit Card">Debit Card</option>
            <option value="Credit Card">Credit Card</option>
        </select><br><br>
        
        <input type="submit" value="Place Order">
    </form>
</body>
</html>
