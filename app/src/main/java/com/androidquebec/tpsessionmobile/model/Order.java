package com.androidquebec.tpsessionmobile.model;

import java.util.Date;

public class Order {

    private static int orderNumber;

    public static int generateOrderNumber () {
        return  ++ orderNumber;
    }

    private int orderRef;
    private Date date;

    public Order(int orderRef, Date date) {
        this.orderRef = orderRef;
        this.date = date;
    }

    public int getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(int orderRef) {
        this.orderRef = orderRef;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
