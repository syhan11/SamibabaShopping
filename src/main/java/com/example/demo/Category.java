package com.example.demo;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    @Size(max=255)
    private String title;

    @Size(max=255)
    private String img;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Product> products;

    public Category() {
        this.setImg("");
        this.setProducts(null);
    }

    public Category(String title, String img) {
        this.title = title;
        this.img = img;
        this.setProducts(null);
    }

    public Category(String title) {

        this.title = title;
        this.setImg("");
        this.setProducts(null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
