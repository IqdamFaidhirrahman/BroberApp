package com.example.broberapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BarberActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BarberAdapter barberAdapter;
    private ArrayList<Barber> barberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        barberList = new ArrayList<>();
        barberList.add(new Barber(1, "Rafael Malik"));
        barberList.add(new Barber(2, "Rian Martino"));
        barberList.add(new Barber(3, "Devano Tito"));
        barberList.add(new Barber(4, "Marsel Calvin"));
        barberList.add(new Barber(5, "Zaviero Denandra"));

        barberAdapter = new BarberAdapter(barberList, new BarberAdapter.OnRatingClickListener() {
            @Override
            public void onRatingClick(int barberId) {
                // Mengarah ke RatingActivity dengan barberId yang dikirimkan
                Intent intent = new Intent(BarberActivity.this, RatingActivity.class);
                intent.putExtra("barber_id", barberId);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(barberAdapter);
    }
}
