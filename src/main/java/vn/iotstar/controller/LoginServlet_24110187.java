package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.User_24110187;
import vn.iotstar.service.UserService_24110187;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet_24110187 extends HttpServlet {

    private final UserService_24110187 userService = new UserService_24110187();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/common/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");

        User_24110187 user = userService.login(username, password);

        if (user == null) {
            req.setAttribute("error", "Sai tên đăng nhập/mật khẩu, hoặc tài khoản chưa kích hoạt!");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/WEB-INF/views/common/login.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);
        session.setMaxInactiveInterval(30 * 60);

        if ("on".equals(remember)) {
            Cookie cookie = new Cookie("remember_user", user.getUsername());
            cookie.setMaxAge(7 * 24 * 60 * 60);
            cookie.setPath(req.getContextPath());
            resp.addCookie(cookie);
        }

        if (Boolean.TRUE.equals(user.getAdmin())) {
            resp.sendRedirect(req.getContextPath() + "/admin/home");
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }
}