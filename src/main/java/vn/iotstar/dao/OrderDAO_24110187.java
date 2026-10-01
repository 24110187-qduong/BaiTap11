package vn.iotstar.dao;

import vn.iotstar.entity.CartItem_24110187;
import vn.iotstar.entity.Order_24110187;
import vn.iotstar.util.DBConnect_24110187;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO_24110187 {

    /**
     * Tạo đơn hàng mới + chi tiết đơn hàng
     */
    public int createOrder(Order_24110187 order, List<CartItem_24110187> items) {
        String sqlOrder = "INSERT INTO Orders(Username, TotalAmount, PaymentMethod, Status, Address, Phone, Note) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlDetail = "INSERT INTO OrderDetails(OrderId, Videoid, Price, Quantity) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnect_24110187.getConnection()) {
            conn.setAutoCommit(false);

            int orderId;
            try (PreparedStatement ps = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, order.getUsername());
                ps.setLong(2, order.getTotalAmount());
                ps.setString(3, order.getPaymentMethod());
                ps.setString(4, order.getStatus());
                ps.setString(5, order.getAddress());
                ps.setString(6, order.getPhone());
                ps.setString(7, order.getNote());
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) orderId = rs.getInt(1);
                    else { conn.rollback(); return -1; }
                }
            }

            try (PreparedStatement ps = conn.prepareStatement(sqlDetail)) {
                for (CartItem_24110187 item : items) {
                    ps.setInt(1, orderId);
                    ps.setString(2, item.getVideoid());
                    ps.setLong(3, item.getPrice());
                    ps.setInt(4, item.getQuantity());
                    ps.addBatch();
                }
                ps.executeBatch();
            }

            conn.commit();
            return orderId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Lấy danh sách đơn hàng của 1 user
     */
    public List<Order_24110187> findByUsername(String username) {
        List<Order_24110187> list = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE Username = ? ORDER BY OrderDate DESC";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    /**
     * Lấy chi tiết đơn hàng
     */
    public List<CartItem_24110187> findDetails(int orderId) {
        List<CartItem_24110187> list = new ArrayList<>();
        String sql = "SELECT od.Videoid, od.Price, od.Quantity, v.Title, v.Poster " +
                "FROM OrderDetails od JOIN Videos v ON od.Videoid = v.Videoid " +
                "WHERE od.OrderId = ?";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CartItem_24110187 it = new CartItem_24110187();
                    it.setVideoid(rs.getString("Videoid"));
                    it.setTitle(rs.getString("Title"));
                    it.setPoster(rs.getString("Poster"));
                    it.setPrice(rs.getLong("Price"));
                    it.setQuantity(rs.getInt("Quantity"));
                    list.add(it);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    private Order_24110187 mapRow(ResultSet rs) throws SQLException {
        Order_24110187 o = new Order_24110187();
        o.setOrderId(rs.getInt("OrderId"));
        o.setUsername(rs.getString("Username"));
        o.setOrderDate(rs.getTimestamp("OrderDate"));
        o.setTotalAmount(rs.getLong("TotalAmount"));
        o.setPaymentMethod(rs.getString("PaymentMethod"));
        o.setStatus(rs.getString("Status"));
        o.setAddress(rs.getString("Address"));
        o.setPhone(rs.getString("Phone"));
        o.setNote(rs.getString("Note"));
        return o;
    }
}