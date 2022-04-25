package com.example.myapplication;

public class MainModel {
    int price;
    byte[] image;
    String name,description;
    int id;

    public MainModel() {
    }

    public MainModel(int id,byte[] image, String name, int price, String description) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.description = description;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
