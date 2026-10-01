package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.dao.OrderDAO_24110187;
import vn.iotstar.entity.CartItem_24110187;
import vn.iotstar.entity.Order_24110187;
import vn.iotstar.entity.User_24110187;
import vn.iotstar.service.CartService_24110187;

import java.io.IOException;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutServlet_24110187 extends HttpServlet {

    private final CartService_24110187 cartService = new CartService_24110187();
    private final OrderDAO_24110187 orderDAO = new OrderDAO_24110187();

    @SuppressWarnings("unchecked")
    private List<CartItem_24110187> getCart(HttpSession session) {
        return (List<CartItem_24110187>) session.getAttribute("cart");
    }

    private boolean isUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return false;
        }
        User_24110187 user = (User_24110187) session.getAttribute("user");
        if (Boolean.TRUE.equals(user.getAdmin())) {
            resp.sendRedirect(req.getContextPath() + "/admin/home");
            return false;
        }
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (!isUser(req, resp)) return;

        HttpSession session = req.getSession();
        List<CartItem_24110187> cart = getCart(session);
        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }

        User_24110187 user = (User_24110187) session.getAttribute("user");
        req.setAttribute("cart", cart);
        req.setAttribute("total", cartService.getTotal(cart));
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/views/user/checkout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (!isUser(req, resp)) return;

        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User_24110187 user = (User_24110187) session.getAttribute("user");
        List<CartItem_24110187> cart = getCart(session);

        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }

        String address = req.getParameter("address");
        String phone   = req.getParameter("phone");
        String note    = req.getParameter("note");

        if (address == null || address.isBlank() || phone == null || phone.isBlank()) {
            req.setAttribute("error", "Vui lòng nhập đầy đủ địa chỉ và số điện thoại!");
            req.setAttribute("cart", cart);
            req.setAttribute("total", cartService.getTotal(cart));
            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/views/user/checkout.jsp").forward(req, resp);
            return;
        }

        Order_24110187 order = new Order_24110187();
        order.setUsername(user.getUsername());
        order.setTotalAmount(cartService.getTotal(cart));
        order.setPaymentMethod("COD");
        order.setStatus("PENDING");
        order.setAddress(address);
        order.setPhone(phone);
        order.setNote(note);

        int orderId = orderDAO.createOrder(order, cart);

        if (orderId <= 0) {
            req.setAttribute("error", "Đặt hàng thất bại, vui lòng thử lại!");
            req.setAttribute("cart", cart);
            req.setAttribute("total", cartService.getTotal(cart));
            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/views/user/checkout.jsp").forward(req, resp);
            return;
        }

        cartService.clearCart(cart);
        session.setAttribute("orderSuccess", "Đặt hàng COD thành công! Mã đơn hàng: #" + orderId);
        resp.sendRedirect(req.getContextPath() + "/orders");
    }
}