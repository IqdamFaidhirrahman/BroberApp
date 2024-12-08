package com.example.broberapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {

    private RecyclerView rvPaymentMethods;
    private Button btnProceedPayment;
    private String selectedPaymentMethod = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        TextView tvBookingDetails = findViewById(R.id.tvBookingDetails);
        rvPaymentMethods = findViewById(R.id.rvPaymentMethods);
        btnProceedPayment = findViewById(R.id.btnProceedPayment);

        // Retrieve selected date and time from Intent
        String date = getIntent().getStringExtra("selectedDate");
        String time = getIntent().getStringExtra("selectedTime");

        // Log the received data
        Log.d("PaymentActivity", "Received booking data: " + date + " - " + time);

        // Set booking details in TextView
        if (date != null && time != null) {
            tvBookingDetails.setText("Booking Details:\nDate: " + date + "\nTime: " + time);
        } else {
            tvBookingDetails.setText("Booking Details:\nNo details available.");
        }

        // Set up RecyclerView for payment methods
        List<String> paymentMethods = getPaymentMethods();
        PaymentMethodAdapter adapter = new PaymentMethodAdapter(paymentMethods, method -> {
            selectedPaymentMethod = method;
            Toast.makeText(this, "Selected: " + selectedPaymentMethod, Toast.LENGTH_SHORT).show();
        });

        rvPaymentMethods.setLayoutManager(new LinearLayoutManager(this));
        rvPaymentMethods.setAdapter(adapter);

        // Proceed to payment button click listener
        btnProceedPayment.setOnClickListener(v -> {
            if (selectedPaymentMethod == null) {
                Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show();
                return;
            }

            // Send the selected payment method to the next activity
            Intent intent = new Intent(PaymentActivity.this, PaymentSuccessActivity.class);
            intent.putExtra("payment_method", selectedPaymentMethod);
            intent.putExtra("selectedDate", date);
            intent.putExtra("selectedTime", time);
            startActivity(intent);
        });
    }

    private List<String> getPaymentMethods() {
        List<String> methods = new ArrayList<>();
        methods.add("Bank BCA");
        methods.add("Bank Mandiri");
        methods.add("GoPay");
        methods.add("ShopeePay");
        methods.add("OVO");
        return methods;
    }
}
