package vn.iotstar.service;

import vn.iotstar.dao.UserDAO_24110187;
import vn.iotstar.entity.User_24110187;

import java.util.List;

public class UserService_24110187 {

    private final UserDAO_24110187 userDAO = new UserDAO_24110187();

    /**
     * Đăng ký — lưu mật khẩu plain text
     */
    public boolean register(User_24110187 user) {
        if (userDAO.findById(user.getUsername()) != null) return false;
        if (userDAO.findByEmail(user.getEmail()) != null) return false;

        // KHÔNG hash, lưu plain text
        user.setAdmin(false);
        user.setActive(false);

        return userDAO.insert(user);
    }

    public boolean activate(String username) {
        return userDAO.updateActive(username, true);
    }

    /**
     * Đăng nhập — so sánh plain text
     */
    public User_24110187 login(String username, String rawPassword) {
        User_24110187 user = userDAO.findById(username);
        if (user == null) {
            System.out.println("[LOGIN] Không tìm thấy user: " + username);
            return null;
        }

        System.out.println("[LOGIN] DB pass: [" + user.getPassword() + "]");
        System.out.println("[LOGIN] Form pass: [" + rawPassword + "]");
        System.out.println("[LOGIN] Active: " + user.getActive());

        if (rawPassword == null || !rawPassword.equals(user.getPassword())) {
            System.out.println("[LOGIN] Sai mật khẩu");
            return null;
        }

        if (!Boolean.TRUE.equals(user.getActive())) {
            System.out.println("[LOGIN] Tài khoản chưa kích hoạt");
            return null;
        }

        System.out.println("[LOGIN] Thành công: " + username);
        return user;
    }

    public User_24110187 findById(String username) { return userDAO.findById(username); }
    public User_24110187 findByEmail(String email) { return userDAO.findByEmail(email); }

    public List<User_24110187> findAll() { return userDAO.findAll(); }
    public List<User_24110187> findAll(int page, int pageSize) { return userDAO.findAll(page, pageSize); }
    public int countAll() { return userDAO.countAll(); }

    /**
     * Lưu user mới (dùng cho admin thêm user)
     */
    public boolean save(User_24110187 user, String rawPassword) {
        if (rawPassword != null && !rawPassword.isBlank()) {
            user.setPassword(rawPassword); // plain text
        }
        return userDAO.insert(user);
    }

    /**
     * Cập nhật user (dùng cho admin sửa user)
     */
    public boolean update(User_24110187 user, String rawPassword) {
        if (rawPassword != null && !rawPassword.isBlank()) {
            user.setPassword(rawPassword); // plain text
        } else {
            User_24110187 old = userDAO.findById(user.getUsername());
            user.setPassword(old.getPassword());
        }
        return userDAO.update(user);
    }

    public boolean delete(String username) {
        return userDAO.delete(username);
    }
}