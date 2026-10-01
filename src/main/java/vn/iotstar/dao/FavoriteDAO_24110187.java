package vn.iotstar.dao;

import vn.iotstar.util.DBConnect_24110187;

import java.sql.*;

public class FavoriteDAO_24110187 {

    public int countByVideo(String videoid) {
        String sql = "SELECT COUNT(*) FROM Favorites WHERE Videoid = ?";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, videoid);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public boolean insert(String videoid, String username) {
        String sql = "INSERT INTO Favorites(LikedDate, Videoid, Username) VALUES (CURDATE(), ?, ?)";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, videoid);
            ps.setString(2, username);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}