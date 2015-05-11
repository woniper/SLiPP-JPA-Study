package net.woniper.jpa.domain.otmAndmto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by woniper on 15. 4. 30..
 */
@Entity(name = "tbl_user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    private String username;

    private String nickName;

    private String address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int totalPrice() {
        int totalPrice = 0;

        for (Order order : orders) {
            totalPrice += order.getPrice();
        }

        return totalPrice;
    }

    public boolean addOrder(Order order) {
        if(orders == null)
            orders = new ArrayList<>();

        return this.orders.add(order);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", nickName='" + nickName + '\'' +
                ", address='" + address + '\'' +
                ", orders=" + orders +
                '}';
    }
}
