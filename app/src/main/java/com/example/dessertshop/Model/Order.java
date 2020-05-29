package com.example.dessertshop.Model;

public class Order {
    private String ProductId;
    private String ProductName;
    private String Quantity;
    private String Harga;
    private String Diskon;

    public Order() {
    }

    public Order(String productId, String productName, String quantity, String harga, String diskon) {
        ProductId = productId;
        ProductName = productName;
        Quantity = quantity;
        Harga = harga;
        Diskon = diskon;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getDiskon() {
        return Diskon;
    }

    public void setDiskon(String diskon) {
        Diskon = diskon;
    }
}
