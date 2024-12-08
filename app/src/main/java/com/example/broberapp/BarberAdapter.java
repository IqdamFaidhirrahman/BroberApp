package com.example.broberapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BarberAdapter extends RecyclerView.Adapter<BarberAdapter.BarberViewHolder> {

    private ArrayList<Barber> barberList;
    private OnRatingClickListener onRatingClickListener;

    public BarberAdapter(ArrayList<Barber> barberList, OnRatingClickListener onRatingClickListener) {
        this.barberList = barberList;
        this.onRatingClickListener = onRatingClickListener;
    }

    @Override
    public BarberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barber, parent, false);
        return new BarberViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BarberViewHolder holder, int position) {
        Barber barber = barberList.get(position);
        holder.barberNameTextView.setText(barber.getName());

        // Mengatur tombol Rating
        holder.ratingButton.setOnClickListener(v -> {
            if (onRatingClickListener != null) {
                onRatingClickListener.onRatingClick(barber.getId());  // Mengirim barberId
            }
        });
    }

    @Override
    public int getItemCount() {
        return barberList.size();
    }

    public static class BarberViewHolder extends RecyclerView.ViewHolder {
        public TextView barberNameTextView;
        public Button ratingButton;

        public BarberViewHolder(View itemView) {
            super(itemView);
            barberNameTextView = itemView.findViewById(R.id.barber_name_text_view);
            ratingButton = itemView.findViewById(R.id.rating_button);
        }
    }

    // Listener untuk menangani klik tombol Rating
    public interface OnRatingClickListener {
        void onRatingClick(int barberId);
    }
}
