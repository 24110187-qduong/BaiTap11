package vn.iotstar.entity;

import java.io.Serializable;

public class CartItem_24110187 implements Serializable {
    private String videoid;
    private String title;
    private String poster;
    private long price;
    private int quantity;

    public CartItem_24110187() {}

    public CartItem_24110187(String videoid, String title, String poster, long price, int quantity) {
        this.videoid = videoid;
        this.title = title;
        this.poster = poster;
        this.price = price;
        this.quantity = quantity;
    }

    public long getSubtotal() {
        return price * quantity;
    }

    public String getVideoid() { return videoid; }
    public void setVideoid(String videoid) { this.videoid = videoid; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }

    public long getPrice() { return price; }
    public void setPrice(long price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}