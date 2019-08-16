package com.example.demo;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Entity
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="order_id")
    private String orderId;

    @Column(name="orddate")
    private String orddate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="orduser_id")
    private User orduser;

//    @ManyToMany
//    private Set<Product> products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ordprod_id")
    private Product ordprod;

    @Column(name="qty")
    public int qty;

    @Column(name="status")
    private int status;   // cancel=1; standby=2; ordered=3; shipped=4; wish = 5; cancelAdmin=6

    public OrderHistory() {

        SimpleDateFormat date = new SimpleDateFormat("MMddyyyy");
        this.orddate = date.format( new Date() );

    }

    public OrderHistory(User orduser) {
        this.orduser = orduser;

        SimpleDateFormat date = new SimpleDateFormat("MMddyyyy");
        this.orddate = date.format( new Date() );
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

    public String getOrddate() {
        return orddate;
    }

    public void setOrddate(String orddate) {
        this.orddate = orddate;
    }

    public User getOrduser() {
        return orduser;
    }

    public void setOrduser(User orduser) {
        this.orduser = orduser;
    }

    public Product getOrdprod() {
        return ordprod;
    }

    public void setOrdprod(Product ordprod) {
        this.ordprod = ordprod;
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
