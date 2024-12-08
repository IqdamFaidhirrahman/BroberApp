package com.example.broberapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGetStarted = findViewById(R.id.btnGetStarted);
        btnGetStarted.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}