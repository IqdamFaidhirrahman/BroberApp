package com.example.broberapp;

public class BarberRating {

    private int id;
    private int barberId;
    private int rating;
    private String comment;

    public BarberRating(int id, int barberId, int rating, String comment) {
        this.id = id;
        this.barberId = barberId;
        this.rating = rating;
        this.comment = comment;
    }

    // Getter dan Setter untuk setiap properti
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBarberId() {
        return barberId;
    }

    public void setBarberId(int barberId) {
        this.barberId = barberId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
