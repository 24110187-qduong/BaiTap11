package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.User_24110187;
import vn.iotstar.service.MailService_24110187;
import vn.iotstar.service.UserService_24110187;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet_24110187 extends HttpServlet {

    private final UserService_24110187 userService = new UserService_24110187();
    private final MailService_24110187 mailService = new MailService_24110187();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/common/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirm  = req.getParameter("confirm");
        String fullname = req.getParameter("fullname");
        String email    = req.getParameter("email");
        String phone    = req.getParameter("phone");

        if (username == null || username.isBlank() || password == null || password.isBlank()
                || email == null || email.isBlank()) {
            req.setAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
            req.getRequestDispatcher("/WEB-INF/views/common/register.jsp").forward(req, resp);
            return;
        }

        if (!password.equals(confirm)) {
            req.setAttribute("error", "Mật khẩu xác nhận không khớp!");
            req.getRequestDispatcher("/WEB-INF/views/common/register.jsp").forward(req, resp);
            return;
        }

        if (userService.findById(username) != null) {
            req.setAttribute("error", "Tên đăng nhập đã tồn tại!");
            req.getRequestDispatcher("/WEB-INF/views/common/register.jsp").forward(req, resp);
            return;
        }

        if (userService.findByEmail(email) != null) {
            req.setAttribute("error", "Email đã được sử dụng!");
            req.getRequestDispatcher("/WEB-INF/views/common/register.jsp").forward(req, resp);
            return;
        }

        User_24110187 user = new User_24110187();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setImages("default.png");

        if (!userService.register(user)) {
            req.setAttribute("error", "Đăng ký thất bại, vui lòng thử lại!");
            req.getRequestDispatcher("/WEB-INF/views/common/register.jsp").forward(req, resp);
            return;
        }

        String otp = mailService.generateOtp();
        HttpSession session = req.getSession();
        session.setAttribute("otp", otp);
        session.setAttribute("otp_username", username);
        session.setAttribute("otp_email", email);
        session.setAttribute("otp_fullname", fullname);
        session.setAttribute("otp_created", System.currentTimeMillis());

        try {
            mailService.sendOtp(email, fullname, otp);
        } catch (Exception e) {
            req.setAttribute("error", "Không thể gửi mail OTP. Vui lòng thử lại!");
            req.getRequestDispatcher("/WEB-INF/views/common/register.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/verify-otp");
    }
}