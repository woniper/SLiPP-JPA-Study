package net.woniper.jpa.domain.oto;

import javax.persistence.*;

/**
 * Created by woniper on 15. 5. 8..
 */
@Entity
public class Person {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToOne
    @JoinColumn(name = "cellular_id")
    private Cellular cellular;

    public Person() {}

    public Person(String name, Cellular cellular) {
        this.name = name;
        this.cellular = cellular;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cellular getCellular() {
        return cellular;
    }

    public void setCellular(Cellular cellular) {
        this.cellular = cellular;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cellular=" + cellular +
                '}';
    }
}
