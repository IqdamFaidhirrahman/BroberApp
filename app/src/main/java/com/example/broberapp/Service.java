// Service.java
package com.example.broberapp;

public class Service {
    private String name;
    private String description;
    private double price;
    private int iconResId;

    public Service(String name, String description, double price, int iconResId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.iconResId = iconResId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getIconResId() {
        return iconResId;
    }
}
