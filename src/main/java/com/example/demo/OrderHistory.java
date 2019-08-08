package com.example.demo;

import javax.persistence.*;
import java.util.Set;

@Entity
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="order_id")
    private String orderId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="orduser_id")
    private User orduser;

    @ManyToMany
    private Set<Product> products;


    @Column(name="qty")
    private int qty;

    @Column(name="status")
    private int status;   // cancel=1; standby=2; ordered=3; shipped=4; wish = 5; cancelAdmin=6

    public OrderHistory() {
        this.products = null;
    }

    public OrderHistory(User orduser) {
        this.orduser = orduser;
        this.products = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public User getOrduser() {
        return orduser;
    }

    public void setOrduser(User orduser) {
        this.orduser = orduser;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
