package net.woniper.jpa.domain;

import javax.persistence.*;

/**
 * Created by woniper on 15. 4. 30..
 */
@Entity(name = "tbl_order")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;

    private String orderName;

    private String note;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Order(String orderName, String note, int price, User user) {
        this.orderName = orderName;
        this.note = note;
        this.price = price;
        this.user = user;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderName='" + orderName + '\'' +
                ", note='" + note +
                '}' + "\n";
    }
}
