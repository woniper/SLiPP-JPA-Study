package net.woniper.jpa.domain.oto;

import javax.persistence.*;

/**
 * Created by woniper on 15. 5. 8..
 */
@Entity
public class Cellular {

    @Id
    @GeneratedValue
    private int id;

    private int number;

    public Cellular() {}

    public Cellular(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Cellular{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}
