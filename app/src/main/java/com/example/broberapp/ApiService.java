package com.example.broberapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("history.php")  // Ganti dengan endpoint yang sesuai
    Call<List<Booking>> getBookingDetails();
}
