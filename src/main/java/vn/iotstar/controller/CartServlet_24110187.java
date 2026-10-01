package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.CartItem_24110187;
import vn.iotstar.entity.User_24110187;
import vn.iotstar.service.CartService_24110187;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart")
public class CartServlet_24110187 extends HttpServlet {

    private final CartService_24110187 cartService = new CartService_24110187();

    @SuppressWarnings("unchecked")
    private List<CartItem_24110187> getCart(HttpSession session) {
        List<CartItem_24110187> cart = (List<CartItem_24110187>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
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

        String action = req.getParameter("action");
        if (action == null) action = "view";

        HttpSession session = req.getSession();
        List<CartItem_24110187> cart = getCart(session);

        switch (action) {
            case "remove" -> {
                cartService.removeFromCart(cart, req.getParameter("id"));
                resp.sendRedirect(req.getContextPath() + "/cart");
            }
            case "clear" -> {
                cartService.clearCart(cart);
                resp.sendRedirect(req.getContextPath() + "/cart");
            }
            default -> {
                req.setAttribute("cart", cart);
                req.setAttribute("total", cartService.getTotal(cart));
                req.setAttribute("maxQuantity", cartService.getMaxQuantity());
                req.getRequestDispatcher("/WEB-INF/views/user/cart.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (!isUser(req, resp)) return;

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) action = "add";

        HttpSession session = req.getSession();
        List<CartItem_24110187> cart = getCart(session);

        switch (action) {
            case "add" -> {
                String videoid = req.getParameter("videoid");
                int quantity = 1;
                try { quantity = Integer.parseInt(req.getParameter("quantity")); } catch (Exception ignored) {}
                cartService.addToCart(cart, videoid, quantity);
                resp.sendRedirect(req.getContextPath() + "/cart");
            }
            case "update" -> {
                String videoid = req.getParameter("videoid");
                int quantity = 1;
                try { quantity = Integer.parseInt(req.getParameter("quantity")); } catch (Exception ignored) {}
                cartService.updateQuantity(cart, videoid, quantity);
                resp.sendRedirect(req.getContextPath() + "/cart");
            }
            default -> resp.sendRedirect(req.getContextPath() + "/cart");
        }
    }
}