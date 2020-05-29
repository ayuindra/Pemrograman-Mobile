package com.example.dessertshop.Model;

public class Food {
    private String Name, Image, Description, Harga, Diskon, MenuId;

    public Food() {
    }

    public Food(String name, String image, String description, String harga, String diskon, String menuId) {
        Name = name;
        Image = image;
        Description = description;
        Harga = harga;
        Diskon = diskon;
        MenuId = menuId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}
