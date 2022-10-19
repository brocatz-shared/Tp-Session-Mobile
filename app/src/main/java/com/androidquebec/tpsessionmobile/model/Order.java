package com.androidquebec.tpsessionmobile.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Order {

    private static int orderNumber;

    public static int generateOrderNumber () {
        return  ++ orderNumber;
    }

    private int orderRef;
    private LocalDateTime date;

    public Order(int orderRef, LocalDateTime date) {
        this.orderRef = orderRef;
        this.date = date;
    }

    public int getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(int orderRef) {
        this.orderRef = orderRef;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
