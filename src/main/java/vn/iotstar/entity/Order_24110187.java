package vn.iotstar.entity;

import java.util.Date;
import java.util.List;

public class Order_24110187 {
    private Integer orderId;
    private String username;
    private Date orderDate;
    private long totalAmount;
    private String paymentMethod;
    private String status;
    private String address;
    private String phone;
    private String note;
    private List<CartItem_24110187> items;

    public Order_24110187() {}

    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public long getTotalAmount() { return totalAmount; }
    public void setTotalAmount(long totalAmount) { this.totalAmount = totalAmount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public List<CartItem_24110187> getItems() { return items; }
    public void setItems(List<CartItem_24110187> items) { this.items = items; }
}