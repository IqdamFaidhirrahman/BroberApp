package com.example.broberapp;

public class Rating {

    private int id;
    private int barberId;
    private float rating;
    private String comment;

    public Rating(int id, int barberId, float rating, String comment) {
        this.id = id;
        this.barberId = barberId;
        this.rating = rating;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public int getBarberId() {
        return barberId;
    }

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
