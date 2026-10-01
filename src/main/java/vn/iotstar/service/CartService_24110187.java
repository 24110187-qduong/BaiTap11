package vn.iotstar.service;

import vn.iotstar.dao.VideoDAO_24110187;
import vn.iotstar.entity.CartItem_24110187;
import vn.iotstar.entity.Video_24110187;

import java.util.ArrayList;
import java.util.List;

public class CartService_24110187 {

    private static final int MAX_QUANTITY = 10;  // Giới hạn số lượng mỗi item
    private final VideoDAO_24110187 videoDAO = new VideoDAO_24110187();

    /**
     * Tính giá bán của video (VD: 50000 + views * 100)
     */
    public long calculatePrice(Video_24110187 v) {
        if (v == null) return 0;
        return 50000L + (v.getViews() != null ? v.getViews() : 0) * 100L;
    }

    /**
     * Thêm sản phẩm vào giỏ
     */
    public void addToCart(List<CartItem_24110187> cart, String videoid, int quantity) {
        if (cart == null) cart = new ArrayList<>();
        if (quantity < 1) quantity = 1;

        for (CartItem_24110187 item : cart) {
            if (item.getVideoid().equals(videoid)) {
                int newQty = item.getQuantity() + quantity;
                item.setQuantity(Math.min(newQty, MAX_QUANTITY));
                return;
            }
        }

        Video_24110187 v = videoDAO.findById(videoid);
        if (v == null) return;

        CartItem_24110187 item = new CartItem_24110187();
        item.setVideoid(v.getVideoid());
        item.setTitle(v.getTitle());
        item.setPoster(v.getPoster());
        item.setPrice(calculatePrice(v));
        item.setQuantity(Math.min(quantity, MAX_QUANTITY));
        cart.add(item);
    }

    /**
     * Cập nhật số lượng
     */
    public void updateQuantity(List<CartItem_24110187> cart, String videoid, int newQuantity) {
        if (cart == null) return;
        if (newQuantity < 1) newQuantity = 1;
        if (newQuantity > MAX_QUANTITY) newQuantity = MAX_QUANTITY;

        for (CartItem_24110187 item : cart) {
            if (item.getVideoid().equals(videoid)) {
                item.setQuantity(newQuantity);
                return;
            }
        }
    }

    /**
     * Xóa 1 sản phẩm
     */
    public void removeFromCart(List<CartItem_24110187> cart, String videoid) {
        if (cart == null) return;
        cart.removeIf(item -> item.getVideoid().equals(videoid));
    }

    /**
     * Xóa toàn bộ giỏ
     */
    public void clearCart(List<CartItem_24110187> cart) {
        if (cart != null) cart.clear();
    }

    /**
     * Tính tổng tiền
     */
    public long getTotal(List<CartItem_24110187> cart) {
        if (cart == null) return 0;
        long total = 0;
        for (CartItem_24110187 item : cart) {
            total += item.getSubtotal();
        }
        return total;
    }

    /**
     * Đếm tổng số lượng item trong giỏ
     */
    public int countItems(List<CartItem_24110187> cart) {
        if (cart == null) return 0;
        int count = 0;
        for (CartItem_24110187 item : cart) count += item.getQuantity();
        return count;
    }

    public int getMaxQuantity() { return MAX_QUANTITY; }
}