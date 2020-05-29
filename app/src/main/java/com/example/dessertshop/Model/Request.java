package com.example.dessertshop.Model;

import java.util.List;

public class Request {
    private String phone;
    private String name;
    private String alamat;
    private String total;
    private String status;
    private String comment;
    private List<Order> desserts; //list of dessert order

    public Request() {
    }

    public Request(String phone, String name, String alamat, String total, String status, String comment, List<Order> desserts) {
        this.phone = phone;
        this.name = name;
        this.alamat = alamat;
        this.total = total;
        this.status = status;
        this.comment = comment;
        this.desserts = desserts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Order> getDesserts() {
        return desserts;
    }

    public void setDesserts(List<Order> desserts) {
        this.desserts = desserts;
    }
}
