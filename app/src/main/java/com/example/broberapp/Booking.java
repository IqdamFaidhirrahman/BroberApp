package com.example.broberapp;

public class Booking {

    private String date;
    private String time;
    private String staff;

    // Constructor
    public Booking(String date, String time, String staff) {
        this.date = date;
        this.time = time;
        this.staff = staff;
    }

    // Getter and Setter
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }
}
