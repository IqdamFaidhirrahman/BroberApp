package com.example.broberapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BarberRatingDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "barber_ratings.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "ratings";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_BARBER_ID = "barber_id"; // Menambahkan kolom barber_id
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_COMMENT = "comment";

    public BarberRatingDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BARBER_ID + " INTEGER, " + // Kolom barber_id
                COLUMN_RATING + " INTEGER, " +
                COLUMN_COMMENT + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertRating(int barberId, int rating, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BARBER_ID, barberId);
        values.put(COLUMN_RATING, rating);
        values.put(COLUMN_COMMENT, comment);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<BarberRating> getRatingsByBarberId(int barberId) {
        List<BarberRating> ratings = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{COLUMN_ID, COLUMN_RATING, COLUMN_COMMENT},
                COLUMN_BARBER_ID + " = ?",
                new String[]{String.valueOf(barberId)},
                null, null, null
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                int rating = cursor.getInt(cursor.getColumnIndex(COLUMN_RATING));
                String comment = cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT));
                ratings.add(new BarberRating(id, barberId, rating, comment));
            }
            cursor.close();
        }
        db.close();
        return ratings;
    }
}
