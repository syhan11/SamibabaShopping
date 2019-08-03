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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "orduser_id")
    private User orduser;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinTable(name = "product_id")
    private Product ordproduct;


    @Column(name="qty")
    private int qty;

    @Column(name="status")
    private int status;   // cancel=1; standby=2; ordered=3; shipped=4; wish = 5; cancelAdmin=6

    public OrderHistory() {
    }

    public OrderHistory(User orduser) {
        this.orduser = orduser;
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

    public Product getOrdproduct() {
        return ordproduct;
    }

    public void setOrdproduct(Product ordproduct) {
        this.ordproduct = ordproduct;
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
