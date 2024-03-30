package com.food;

public class Menu {
    private int menuId;
    private int restaurentId;
    private String itemName;
    private String discription;
    private double price;
    private boolean isAvailable;

    public Menu() {

    }

    public Menu(int menuId, int restaurentId, String itemName, String discription, double price, boolean isAvailable) {
        super();
        this.menuId = menuId;
        this.restaurentId = restaurentId;
        this.itemName = itemName;
        this.discription = discription;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getRestaurentId() {
        return restaurentId;
    }

    public void setRestaurentId(int restaurentId) {
        this.restaurentId = restaurentId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
