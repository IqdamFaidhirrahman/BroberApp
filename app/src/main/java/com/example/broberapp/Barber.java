package com.example.broberapp;

public class Barber {

    private int id;
    private String name;

    // Konstruktor Barber
    public Barber(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter untuk id
    public int getId() {
        return id;
    }

    // Setter untuk id
    public void setId(int id) {
        this.id = id;
    }

    // Getter untuk name
    public String getName() {
        return name;
    }

    // Setter untuk name
    public void setName(String name) {
        this.name = name;
    }
}
