package com.example.broberapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RatingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RatingAdapter ratingAdapter;
    private RatingBar ratingBar;
    private EditText commentEditText;
    private Button submitButton;
    private BarberRatingDatabase database;
    private int barberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        recyclerView = findViewById(R.id.recycler_view);
        ratingBar = findViewById(R.id.rating_bar);
        commentEditText = findViewById(R.id.comment_edit_text);
        submitButton = findViewById(R.id.submit_button);

        barberId = getIntent().getIntExtra("barber_id", -1);
        database = new BarberRatingDatabase(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Menampilkan rating yang sudah ada untuk barber
        ratingAdapter = new RatingAdapter(database.getRatingsByBarberId(barberId));
        recyclerView.setAdapter(ratingAdapter);

        submitButton.setOnClickListener(v -> {
            int ratingValue = (int) ratingBar.getRating();
            String comment = commentEditText.getText().toString();

            // Menyimpan rating baru ke database
            database.insertRating(barberId, ratingValue, comment);

            // Memperbarui daftar rating yang ditampilkan
            List<BarberRating> updatedRatings = database.getRatingsByBarberId(barberId);
            ratingAdapter.updateRatings(updatedRatings);

            // Reset form input
            ratingBar.setRating(0);
            commentEditText.setText("");

            // Kembali ke halaman daftar barber
            Intent intent = new Intent(RatingActivity.this, BarberActivity.class);
            startActivity(intent);
        });
    }
}
