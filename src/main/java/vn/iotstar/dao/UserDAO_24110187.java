package vn.iotstar.dao;

import vn.iotstar.entity.User_24110187;
import vn.iotstar.util.DBConnect_24110187;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_24110187 {

    public List<User_24110187> findAll() {
        List<User_24110187> list = new ArrayList<>();
        String sql = "SELECT * FROM Users ORDER BY Username";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<User_24110187> findAll(int page, int pageSize) {
        List<User_24110187> list = new ArrayList<>();
        String sql = "SELECT * FROM Users ORDER BY Username LIMIT ? OFFSET ?";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pageSize);
            ps.setInt(2, (page - 1) * pageSize);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public User_24110187 findById(String username) {
        String sql = "SELECT * FROM Users WHERE Username = ?";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public User_24110187 findByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE Email = ?";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public int countAll() {
        String sql = "SELECT COUNT(*) FROM Users";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public boolean insert(User_24110187 u) {
        String sql = "INSERT INTO Users(Username, Password, Phone, Fullname, Email, Admin, Active, Images) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getPhone());
            ps.setString(4, u.getFullname());
            ps.setString(5, u.getEmail());
            ps.setBoolean(6, u.getAdmin()  != null && u.getAdmin());
            ps.setBoolean(7, u.getActive() != null && u.getActive());
            ps.setString(8, u.getImages());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean update(User_24110187 u) {
        String sql = "UPDATE Users SET Password=?, Phone=?, Fullname=?, Email=?, " +
                "Admin=?, Active=?, Images=? WHERE Username=?";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getPassword());
            ps.setString(2, u.getPhone());
            ps.setString(3, u.getFullname());
            ps.setString(4, u.getEmail());
            ps.setBoolean(5, u.getAdmin()  != null && u.getAdmin());
            ps.setBoolean(6, u.getActive() != null && u.getActive());
            ps.setString(7, u.getImages());
            ps.setString(8, u.getUsername());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean updateActive(String username, boolean active) {
        String sql = "UPDATE Users SET Active = ? WHERE Username = ?";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, active);
            ps.setString(2, username);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean delete(String username) {
        String sql = "DELETE FROM Users WHERE Username = ?";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    private User_24110187 mapRow(ResultSet rs) throws SQLException {
        User_24110187 u = new User_24110187();
        u.setUsername(rs.getString("Username"));
        u.setPassword(rs.getString("Password"));
        u.setPhone(rs.getString("Phone"));
        u.setFullname(rs.getString("Fullname"));
        u.setEmail(rs.getString("Email"));
        u.setAdmin(rs.getBoolean("Admin"));
        u.setActive(rs.getBoolean("Active"));
        u.setImages(rs.getString("Images"));
        return u;
    }
}