package vn.iotstar.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.User_24110187;
import vn.iotstar.service.UserService_24110187;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/users")
public class AdminUserServlet_24110187 extends HttpServlet {

    private final UserService_24110187 userService = new UserService_24110187();
    private static final int PAGE_SIZE = 6;

    private boolean isAdmin(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        User_24110187 user = (session != null) ? (User_24110187) session.getAttribute("user") : null;
        return user != null && Boolean.TRUE.equals(user.getAdmin());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (!isAdmin(req)) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new" -> {
                req.setAttribute("mode", "create");
                req.getRequestDispatcher("/WEB-INF/views/admin/user-form.jsp").forward(req, resp);
            }
            case "edit" -> {
                String username = req.getParameter("username");
                User_24110187 user = userService.findById(username);
                if (user == null) {
                    resp.sendRedirect(req.getContextPath() + "/admin/users");
                    return;
                }
                req.setAttribute("user", user);
                req.setAttribute("mode", "edit");
                req.getRequestDispatcher("/WEB-INF/views/admin/user-form.jsp").forward(req, resp);
            }
            case "delete" -> {
                userService.delete(req.getParameter("username"));
                resp.sendRedirect(req.getContextPath() + "/admin/users");
            }
            default -> {
                int page = 1;
                try { page = Integer.parseInt(req.getParameter("page")); } catch (Exception ignored) {}
                if (page < 1) page = 1;

                List<User_24110187> users = userService.findAll(page, PAGE_SIZE);
                int total = userService.countAll();
                int totalPages = (int) Math.ceil((double) total / PAGE_SIZE);

                req.setAttribute("users", users);
                req.setAttribute("currentPage", page);
                req.setAttribute("totalPages", totalPages);
                req.setAttribute("totalUsers", total);

                req.getRequestDispatcher("/WEB-INF/views/admin/user-list.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (!isAdmin(req)) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) action = "create";

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String email    = req.getParameter("email");
        String phone    = req.getParameter("phone");
        boolean admin   = "on".equals(req.getParameter("admin"));
        boolean active  = "on".equals(req.getParameter("active"));

        User_24110187 user = new User_24110187();
        user.setUsername(username);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAdmin(admin);
        user.setActive(active);
        user.setImages("default.png");

        if ("edit".equals(action)) {
            userService.update(user, password);
        } else {
            if (password == null || password.isBlank()) password = "123456";
            userService.save(user, password);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/users");
    }
}