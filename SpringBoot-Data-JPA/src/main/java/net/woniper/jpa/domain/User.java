package net.woniper.jpa.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by woniper on 15. 4. 30..
 */
@Entity(name = "tbl_user")
public class User extends AbstractPersistable<Integer> {

    private String username;

    private String nickName;

    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate = new Date();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public int totalPrice() {
        int totalPrice = 0;

        for (Order order : orders) {
            totalPrice += order.getPrice();
        }

        return totalPrice;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + getId() +
                ", username='" + username + '\'' +
                ", nickName='" + nickName + '\'' +
                ", address='" + address + '\'' +
                ", orders=" + orders +
                '}';
    }
}
