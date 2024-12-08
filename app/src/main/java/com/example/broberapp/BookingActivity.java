package com.example.broberapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;

import java.util.HashMap;
import java.util.Map;

public class BookingActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private Spinner spinnerTime;
    private Button btnConfirmBooking;
    private String selectedDate = "No date selected";
    private String selectedTime;

    // Firebase reference
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Initialize Firebase Realtime Database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://broberbookingpayment-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = database.getReference("bookings");

        // Initialize UI components
        calendarView = findViewById(R.id.calendarView);
        spinnerTime = findViewById(R.id.spinnerTime);
        btnConfirmBooking = findViewById(R.id.btnConfirmBooking);

        // Set up Spinner for time slots
        String[] timeSlots = {"10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, timeSlots);
        spinnerTime.setAdapter(adapter);

        // Set listener for date selection
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            Log.d("MainActivity", "Selected date: " + selectedDate);
        });

        // Set listener for "Confirm Booking" button
        btnConfirmBooking.setOnClickListener(v -> {
            if (selectedDate.equals("No date selected")) {
                Toast.makeText(BookingActivity.this, "Please select a date", Toast.LENGTH_SHORT).show();
                return;
            }
            selectedTime = spinnerTime.getSelectedItem().toString();
            Log.d("MainActivity", "Selected time: " + selectedTime);

            // Save the booking data to Firebase
            saveBookingToFirebase(selectedDate, selectedTime);
        });
    }

    private void saveBookingToFirebase(String date, String time) {
        // Create a unique ID for the booking
        String bookingId = databaseReference.push().getKey();

        if (bookingId == null) {
            Log.e("MainActivity", "Failed to generate booking ID");
            Toast.makeText(this, "Failed to generate booking ID", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a map to store booking details
        Map<String, String> bookingData = new HashMap<>();
        bookingData.put("date", date);
        bookingData.put("time", time);

        // Log the data that is about to be saved
        Log.d("MainActivity", "Saving booking data: " + bookingData);

        // Save data to Firebase Realtime Database
        databaseReference.child(bookingId).setValue(bookingData, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    // Log and show error if saving failed
                    Log.e("MainActivity", "Failed to save booking: " + databaseError.getMessage());
                    Toast.makeText(BookingActivity.this, "Failed to save booking", Toast.LENGTH_SHORT).show();
                } else {
                    // Successfully saved data, log it
                    Log.d("MainActivity", "Booking saved successfully");
                    Toast.makeText(BookingActivity.this, "Booking saved successfully", Toast.LENGTH_SHORT).show();

                    // Navigate to PaymentActivity
                    navigateToPayment(date, time);
                }
            }
        });
    }

    private void navigateToPayment(String date, String time) {
        // Log the data being passed to PaymentActivity
        Log.d("MainActivity", "Navigating to PaymentActivity with data: " + date + " - " + time);

        // Create an intent to navigate to PaymentActivity
        Intent intent = new Intent(BookingActivity.this, PaymentActivity.class);

        // Pass selected date and time to PaymentActivity
        intent.putExtra("selectedDate", date);
        intent.putExtra("selectedTime", time);

        // Start PaymentActivity
        startActivity(intent);
        finish();  // Optional: to close MainActivity after navigating
    }
}
