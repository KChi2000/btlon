package com.example.myapplication;

public class OrdersModel {
    String soldItemName;
    int orderNum;
    int price;
    int quantity;
    byte [] image;
    String des;
    String username,phone,address;
    public OrdersModel() {
    }

    public OrdersModel(int orderNum,int price, byte [] image,   int quantity,String soldItemName,String des,String username,String phone,String address) {
        this.soldItemName = soldItemName;
        this.orderNum = orderNum;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.des=des;
        this.username=username;
        this.phone=phone;
        this.address=address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public byte [] getImage() {
        return image;
    }

    public void setImage(byte [] image) {
        this.image = image;
    }
    public String getSoldItemName() {
        return soldItemName;
    }

    public void setSoldItemName(String soldItemName) {
        this.soldItemName = soldItemName;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
