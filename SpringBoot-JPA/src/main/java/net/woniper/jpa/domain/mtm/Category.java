package net.woniper.jpa.domain.mtm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by woniper on 15. 5. 12..
 */
@Entity(name = "tbl_category")
public class Category {

    @Id @GeneratedValue
    private int categoryId;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    private List<Product> products;

    public Category() {
    }

    public Category(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean addProduct(Product product) {
        if(products == null)
            products = new ArrayList<>();

        return products.add(product);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
