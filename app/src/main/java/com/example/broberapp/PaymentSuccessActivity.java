package com.example.broberapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentSuccessActivity extends AppCompatActivity {

    private Button btnReturnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        btnReturnHome = findViewById(R.id.btnReturnHome);

        btnReturnHome.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentSuccessActivity.this, BookingActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
