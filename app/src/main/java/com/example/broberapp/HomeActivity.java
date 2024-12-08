package com.example.broberapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView navHome = findViewById(R.id.nav_home);
        navHome.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        // Navigasi ke Book
        TextView navBook = findViewById(R.id.nav_book);
        navBook.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, BookingActivity.class);
            startActivity(intent);
        });

        // Navigasi ke Promo
        TextView navPromo = findViewById(R.id.nav_promo);
        navPromo.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, BarberActivity.class);
            startActivity(intent);
        });

        // Navigasi ke Profile
        TextView navProfile = findViewById(R.id.nav_profile);
        navProfile.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ProfilActivity.class);
            startActivity(intent);
        });
    }
}