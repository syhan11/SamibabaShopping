package com.example.demo;

import javax.persistence.*;

@Entity
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Testing a different generated type - Jacob
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_id")
    private String orderId;

//    @Column(name="user_id")
//    private long userId;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orduser_id")
    private User user;

//    @Column(name="product_id")
//    private long productId;
    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinTable(name = "product_id")
    private Product product;

    @Column(name="qty")
    private int qty;

    @Column(name="status")
    private int status;   // cancel=1; standby=2; ordered=3; shipped=4; cancelAdmin=5;

    public OrderHistory() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
