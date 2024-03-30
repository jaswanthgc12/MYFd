package com.food.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.Cart;
import com.food.CartItem;
import com.food.Order;
import com.food.OrderHistory;
import com.food.OrderItem;
import com.food.User;
import com.food.dao.OrderDAO;
import com.food.dao.OrderHistoryDAO;
import com.food.dao.OrderItemDAO;
import com.food.daoimpl.OrderDAOImpl;
import com.food.daoimpl.OrderHistoryDAOImpl;
import com.food.daoimpl.OrderItemDAOImpl;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDAO orderDAO;
    private OrderHistoryDAO orderHistoryDAO;
    private OrderItemDAO orderItemDAO;

    public void init() {
        orderDAO = new OrderDAOImpl();
        orderHistoryDAO = new OrderHistoryDAOImpl();
        orderItemDAO = new OrderItemDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("loggedInUser");

        if (cart != null && user != null && !cart.getItems().isEmpty()) {
            String paymentMethod = request.getParameter("paymentMethod");
            if (isValidPaymentMethod(paymentMethod)) {
                try {
                    int generatedOrderId = orderDAO.addOrder(createOrder(user, cart, paymentMethod,session));
                    addOrderHistory(generatedOrderId, user, cart);
                    addOrderItems(generatedOrderId, cart);

                    session.removeAttribute("cart");
                    session.setAttribute("order", orderDAO.getOrder(generatedOrderId));
                    response.sendRedirect(request.getContextPath() + "/confirmation.jsp");
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect(request.getContextPath() + "/error.jsp");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/checkout.jsp?error=invalidPayment");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/cart.jsp?error=emptyCart");
        }
    }

    private Order createOrder(User user, Cart cart, String paymentMethod,HttpSession session) {
        Order order = new Order();
        order.setUserId(user.getUserId());
        order.setRestaurentId((int) session.getAttribute("restaurentId"));
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setPaymentMethod(paymentMethod);
        order.setStatus("Pending");
        order.setTotalAmount(calculateTotalAmount(cart));
        return order;
    }

    private void addOrderHistory(int orderId, User user, Cart cart) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setUserId(user.getUserId());
        orderHistory.setOrderId(orderId);
        orderHistory.setOrderDate(now);
        orderHistory.setTotalAmount(calculateTotalAmount(cart));
        orderHistory.setStatus("Delivered"); // Update status based on your business logic
        orderHistoryDAO.addOrderHistory(orderHistory);
    }

    private void addOrderItems(int orderId, Cart cart) {
        for (CartItem cartItem : cart.getItems().values()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setMenuId(cartItem.getRestaurantId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setItemTotal(cartItem.getPrice() * cartItem.getQuantity());
            orderItemDAO.addOrderItem(orderItem);
        }
    }

    private boolean isValidPaymentMethod(String paymentMethod) {
        
        return paymentMethod != null && !paymentMethod.isEmpty();
    }

    private double calculateTotalAmount(Cart cart) {
        double totalAmount = 0;
        for (CartItem item : cart.getItems().values()) {
            totalAmount += item.getPrice() * item.getQuantity();
        }
        return totalAmount;
    }
}
