package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.dao.OrderDAO_24110187;
import vn.iotstar.entity.Order_24110187;
import vn.iotstar.entity.User_24110187;

import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class OrderServlet_24110187 extends HttpServlet {

    private final OrderDAO_24110187 orderDAO = new OrderDAO_24110187();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User_24110187 user = (User_24110187) session.getAttribute("user");
        if (Boolean.TRUE.equals(user.getAdmin())) {
            resp.sendRedirect(req.getContextPath() + "/admin/home");
            return;
        }

        List<Order_24110187> orders = orderDAO.findByUsername(user.getUsername());
        for (Order_24110187 o : orders) {
            o.setItems(orderDAO.findDetails(o.getOrderId()));
        }

        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/WEB-INF/views/user/orders.jsp").forward(req, resp);
    }
}