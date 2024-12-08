package com.example.broberapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.RatingViewHolder> {

    private List<BarberRating> ratingList;

    public RatingAdapter(List<BarberRating> ratingList) {
        this.ratingList = ratingList;
    }

    public void updateRatings(List<BarberRating> updatedRatings) {
        this.ratingList = updatedRatings;
        notifyDataSetChanged();
    }

    @Override
    public RatingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rating, parent, false);
        return new RatingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RatingViewHolder holder, int position) {
        BarberRating rating = ratingList.get(position);
        holder.ratingBar.setRating(rating.getRating());
        holder.commentTextView.setText(rating.getComment());
    }

    @Override
    public int getItemCount() {
        return ratingList.size();
    }

    public static class RatingViewHolder extends RecyclerView.ViewHolder {
        public RatingBar ratingBar;
        public TextView commentTextView;

        public RatingViewHolder(View view) {
            super(view);
            ratingBar = view.findViewById(R.id.rating_bar);
            commentTextView = view.findViewById(R.id.comment_text_view);
        }
    }
}
