package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet_24110187 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) session.invalidate();

        Cookie cookie = new Cookie("remember_user", "");
        cookie.setMaxAge(0);
        cookie.setPath(req.getContextPath());
        resp.addCookie(cookie);

        resp.sendRedirect(req.getContextPath() + "/login");
    }
}