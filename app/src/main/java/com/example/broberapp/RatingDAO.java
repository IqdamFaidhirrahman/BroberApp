package com.example.broberapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class RatingDAO {
    private BarberRatingDatabase dbHelper;

    public RatingDAO(Context context) {
        dbHelper = new BarberRatingDatabase(context);
    }

    // Menyimpan rating baru ke database
    public void insertRating(int barberId, int rating, String comment) {
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // Akses database yang dapat ditulis
        ContentValues values = new ContentValues();
        values.put("barber_id", barberId);
        values.put("rating", rating);
        values.put("comment", comment);

        db.insert("ratings", null, values); // Menyimpan data rating
        db.close();
    }

    // Mengambil rating berdasarkan barber_id
    public List<BarberRating> getRatingsByBarberId(int barberId) {
        List<BarberRating> ratings = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase(); // Akses database yang dapat dibaca

        Cursor cursor = db.query(
                "ratings", // Nama tabel
                new String[]{"id", "barber_id", "rating", "comment"}, // Kolom yang akan diambil
                "barber_id = ?", // Kondisi query
                new String[]{String.valueOf(barberId)}, // Argumen untuk kondisi
                null, null, null); // Group By, Having, Order By (kosong)

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int rating = cursor.getInt(cursor.getColumnIndex("rating"));
                String comment = cursor.getString(cursor.getColumnIndex("comment"));
                ratings.add(new BarberRating(id, barberId, rating, comment));
            }
            cursor.close();
        }
        db.close();
        return ratings;
    }
}
