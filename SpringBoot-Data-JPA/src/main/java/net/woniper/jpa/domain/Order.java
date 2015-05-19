package net.woniper.jpa.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

/**
 * Created by woniper on 15. 4. 30..
 */
@Entity(name = "tbl_order")
public class Order extends AbstractPersistable<Integer> {

    private String orderName;

    private String note;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Order() {
    }

    public Order(String orderName, String note, int price, User user) {
        this.orderName = orderName;
        this.note = note;
        this.price = price;
        this.user = user;
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
                "orderId=" + getId() +
                ", orderName='" + orderName + '\'' +
                ", note='" + note +
                '}' + "\n";
    }
}
