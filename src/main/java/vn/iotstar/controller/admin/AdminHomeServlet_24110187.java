package vn.iotstar.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.User_24110187;

import java.io.IOException;

@WebServlet("/admin/home")
public class AdminHomeServlet_24110187 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User_24110187 user = (session != null) ? (User_24110187) session.getAttribute("user") : null;
        if (user == null || !Boolean.TRUE.equals(user.getAdmin())) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/views/admin/home.jsp").forward(req, resp);
    }
}