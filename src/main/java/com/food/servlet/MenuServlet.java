package com.food.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.food.Menu;
import com.food.dao.MenuDAO;
import com.food.daoimpl.MenuDAOImpl;

@WebServlet("/Menu")
public class MenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    private MenuDAO menuDAO;
    
    public void init() {
        menuDAO = new MenuDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String restaurentId = request.getParameter("restaurentId"); // Corrected parameter name
        if (restaurentId != null && !restaurentId.isEmpty()) {
            try {
                int id = Integer.parseInt(restaurentId);
                List<Menu> menuList = menuDAO.getAllMenuByRestaurant(id);
                request.setAttribute("menuList", menuList);
            } catch (NumberFormatException e) {
                // Handle NumberFormatException (e.g., log or show error message)
                e.printStackTrace();
                // You may want to set an error message attribute here
            }
        }

        // Forward to the menu.jsp for rendering
        RequestDispatcher dispatcher = request.getRequestDispatcher("menu.jsp");
        dispatcher.forward(request, response);
    }
}
