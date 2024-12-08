package com.example.broberapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private List<Booking> bookingList;

    public BookingAdapter(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        holder.dateTextView.setText(booking.getDate());
        holder.timeTextView.setText(booking.getTime());
        holder.staffTextView.setText(booking.getStaff());
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public void updateData(List<Booking> newBookings) {
        bookingList.clear();
        bookingList.addAll(newBookings);
        notifyDataSetChanged();
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView, timeTextView, staffTextView;

        public BookingViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            staffTextView = itemView.findViewById(R.id.staffTextView);
        }
    }
}
