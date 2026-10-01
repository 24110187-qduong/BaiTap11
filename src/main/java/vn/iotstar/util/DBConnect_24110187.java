package vn.iotstar.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect_24110187 {

    private static final String URL =
            "jdbc:mysql://localhost:3306/de_04?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASS = "D12345";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Không tìm thấy MySQL Driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("✅ Kết nối thành công: " + conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}