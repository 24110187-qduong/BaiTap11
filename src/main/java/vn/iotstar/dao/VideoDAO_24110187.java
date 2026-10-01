package vn.iotstar.dao;

import vn.iotstar.entity.Video_24110187;
import vn.iotstar.util.DBConnect_24110187;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideoDAO_24110187 {

    public List<Video_24110187> findByActive(boolean active) {
        List<Video_24110187> list = new ArrayList<>();
        String sql = "SELECT * FROM Videos WHERE Active = ? ORDER BY Videoid";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, active);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Video_24110187 findById(String id) {
        String sql = "SELECT * FROM Videos WHERE Videoid = ?";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public Video_24110187 findByIdWithDetail(String id) {
        String sql = "SELECT v.*, c.Categoryname, " +
                "(SELECT COUNT(*) FROM Shares s WHERE s.Videoid = v.Videoid) AS ShareCount, " +
                "(SELECT COUNT(*) FROM Favorites f WHERE f.Videoid = v.Videoid) AS LikeCount " +
                "FROM Videos v LEFT JOIN Category c ON v.CategoryId = c.CategoryId " +
                "WHERE v.Videoid = ?";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Video_24110187 v = mapRow(rs);
                    v.setCategoryName(rs.getString("Categoryname"));
                    v.setShareCount(rs.getInt("ShareCount"));
                    v.setLikeCount(rs.getInt("LikeCount"));
                    return v;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public List<Video_24110187> findByCategory(int categoryId, int page, int pageSize) {
        List<Video_24110187> list = new ArrayList<>();
        String sql = "SELECT v.*, c.Categoryname, " +
                "(SELECT COUNT(*) FROM Shares s WHERE s.Videoid = v.Videoid) AS ShareCount, " +
                "(SELECT COUNT(*) FROM Favorites f WHERE f.Videoid = v.Videoid) AS LikeCount " +
                "FROM Videos v LEFT JOIN Category c ON v.CategoryId = c.CategoryId " +
                "WHERE v.CategoryId = ? AND v.Active = 1 " +
                "ORDER BY v.Videoid LIMIT ? OFFSET ?";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ps.setInt(2, pageSize);
            ps.setInt(3, (page - 1) * pageSize);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Video_24110187 v = mapRow(rs);
                    v.setCategoryName(rs.getString("Categoryname"));
                    v.setShareCount(rs.getInt("ShareCount"));
                    v.setLikeCount(rs.getInt("LikeCount"));
                    list.add(v);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public int countByCategory(int categoryId) {
        String sql = "SELECT COUNT(*) FROM Videos WHERE CategoryId = ? AND Active = 1";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public void incrementViews(String videoid) {
        String sql = "UPDATE Videos SET Views = Views + 1 WHERE Videoid = ?";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, videoid);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private Video_24110187 mapRow(ResultSet rs) throws SQLException {
        Video_24110187 v = new Video_24110187();
        v.setVideoid(rs.getString("Videoid"));
        v.setTitle(rs.getString("Title"));
        v.setPoster(rs.getString("Poster"));
        v.setViews(rs.getInt("Views"));
        v.setDescription(rs.getString("Description"));
        v.setActive(rs.getBoolean("Active"));
        v.setCategoryId(rs.getInt("CategoryId"));
        return v;
    }
}