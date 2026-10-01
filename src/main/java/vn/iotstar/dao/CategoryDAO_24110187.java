package vn.iotstar.dao;

import vn.iotstar.entity.Category_24110187;
import vn.iotstar.util.DBConnect_24110187;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO_24110187 {

    public List<Category_24110187> findAll() {
        List<Category_24110187> list = new ArrayList<>();
        String sql = "SELECT * FROM Category ORDER BY CategoryId";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<Category_24110187> findAllWithVideoCount() {
        List<Category_24110187> list = new ArrayList<>();
        String sql = "SELECT c.*, COUNT(v.Videoid) AS VideoCount " +
                "FROM Category c LEFT JOIN Videos v ON c.CategoryId = v.CategoryId " +
                "GROUP BY c.CategoryId, c.Categoryname, c.Categorycode, c.Images, c.Status " +
                "ORDER BY c.CategoryId";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Category_24110187 c = mapRow(rs);
                c.setVideoCount(rs.getInt("VideoCount"));
                list.add(c);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Category_24110187 findById(int id) {
        String sql = "SELECT * FROM Category WHERE CategoryId = ?";
        try (Connection conn = DBConnect_24110187.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    private Category_24110187 mapRow(ResultSet rs) throws SQLException {
        Category_24110187 c = new Category_24110187();
        c.setCategoryId(rs.getInt("CategoryId"));
        c.setCategoryname(rs.getString("Categoryname"));
        c.setCategorycode(rs.getString("Categorycode"));
        c.setImages(rs.getString("Images"));
        c.setStatus(rs.getBoolean("Status"));
        return c;
    }
}