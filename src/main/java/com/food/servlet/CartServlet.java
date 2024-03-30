package com.food.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.Cart;
import com.food.CartItem;
import com.food.Menu;
import com.food.dao.MenuDAO;
import com.food.daoimpl.MenuDAOImpl;

@WebServlet("/Cart")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    addCartItem(request, cart);
                    break;
                case "update":
                    updateCartItem(request, cart);
                    break;
                case "remove":
                    removeCartItem(request, cart);
                    break;
                default:
             
                    break;
            }
        }

        request.getSession().setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	request.getRequestDispatcher(getServletInfo()).forward(request, response);
    }

    private void addCartItem(HttpServletRequest request, Cart cart) {
        String itemIdStr = request.getParameter("itemId");
        String quantityStr = request.getParameter("quantity");
        
        if (itemIdStr != null && quantityStr != null) {
            int itemId = Integer.parseInt(itemIdStr);
            int quantity = Integer.parseInt(quantityStr);
            MenuDAO menuDAO = new MenuDAOImpl();
            Menu menuItem = menuDAO.getMenu(itemId);
            HttpSession session = request.getSession();
            session.setAttribute("restaurentId", menuItem.getRestaurentId());
            if (menuItem != null) {
                CartItem item = new CartItem(menuItem.getMenuId(), menuItem.getRestaurentId(), menuItem.getItemName(), quantity, menuItem.getPrice());
                cart.addCartItem(item);
            }
        } else {
            System.out.println("Error: itemId or quantity is null.");
        }
    }

    private void updateCartItem(HttpServletRequest request, Cart cart) {
        String itemIdStr = request.getParameter("itemId");
        String quantityStr = request.getParameter("quantity");

        if (itemIdStr != null && !itemIdStr.isEmpty() && quantityStr != null && !quantityStr.isEmpty()) {
            try {
                int itemId = Integer.parseInt(itemIdStr);
                int quantity = Integer.parseInt(quantityStr);
                cart.updateCart(itemId, quantity);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid itemId or quantity format.");
            }
        } else {
            System.out.println("Error: itemId or quantity is null or empty.");
        }
    }


    private void removeCartItem(HttpServletRequest request, Cart cart) {
        String itemIdStr = request.getParameter("itemId");

        if (itemIdStr != null) {
            int itemId = Integer.parseInt(itemIdStr);
            cart.removeCart(itemId);
        } else {
            System.out.println("Error: itemId is null.");
        }
    }
}
