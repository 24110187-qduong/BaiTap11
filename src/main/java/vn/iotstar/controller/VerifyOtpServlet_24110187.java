package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.service.UserService_24110187;

import java.io.IOException;

@WebServlet("/verify-otp")
public class VerifyOtpServlet_24110187 extends HttpServlet {

    private final UserService_24110187 userService = new UserService_24110187();
    private static final long OTP_EXPIRE_MS = 5 * 60 * 1000;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("otp") == null) {
            resp.sendRedirect(req.getContextPath() + "/register");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/views/common/verify-otp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("otp") == null) {
            resp.sendRedirect(req.getContextPath() + "/register");
            return;
        }

        String inputOtp = req.getParameter("otp");
        String sessionOtp = (String) session.getAttribute("otp");
        String username = (String) session.getAttribute("otp_username");
        Long created = (Long) session.getAttribute("otp_created");

        if (created == null || System.currentTimeMillis() - created > OTP_EXPIRE_MS) {
            req.setAttribute("error", "Mã OTP đã hết hạn!");
            req.getRequestDispatcher("/WEB-INF/views/common/verify-otp.jsp").forward(req, resp);
            return;
        }

        if (inputOtp == null || !inputOtp.trim().equals(sessionOtp)) {
            req.setAttribute("error", "Mã OTP không đúng!");
            req.getRequestDispatcher("/WEB-INF/views/common/verify-otp.jsp").forward(req, resp);
            return;
        }

        if (!userService.activate(username)) {
            req.setAttribute("error", "Kích hoạt thất bại!");
            req.getRequestDispatcher("/WEB-INF/views/common/verify-otp.jsp").forward(req, resp);
            return;
        }

        session.removeAttribute("otp");
        session.removeAttribute("otp_username");
        session.removeAttribute("otp_email");
        session.removeAttribute("otp_fullname");
        session.removeAttribute("otp_created");
        session.setAttribute("success", "Kích hoạt tài khoản thành công!");
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}