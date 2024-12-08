package com.example.broberapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookingAdapter adapter;
    private List<Booking> bookingList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookingAdapter(bookingList);
        recyclerView.setAdapter(adapter);

        fetchBookingData();
    }

    private void fetchBookingData() {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Booking>> call = apiService.getBookingDetails();

        call.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("API Response", new Gson().toJson(response.body()));
                    bookingList = response.body();  // Menyimpan data yang diterima
                    adapter.notifyDataSetChanged(); // Update RecyclerView
                } else {
                    Log.e("API Error", "Response code: " + response.code() + ", message: " + response.message());
                    Toast.makeText(HistoryActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {
                Log.e("Retrofit Error", "Message: " + t.getMessage());
                Toast.makeText(HistoryActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}