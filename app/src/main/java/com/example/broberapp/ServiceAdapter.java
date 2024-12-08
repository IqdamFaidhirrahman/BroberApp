// ServiceAdapter.java
package com.example.broberapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private List<Service> serviceList;
    private OnServiceClickListener listener;


    public interface OnServiceClickListener {
        void onServiceClick(Service service);
    }

    public ServiceAdapter(List<Service> serviceList, OnServiceClickListener listener) {
        this.serviceList = serviceList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service service = serviceList.get(position);
        holder.nameTextView.setText(service.getName());
        holder.priceTextView.setText("$" + service.getPrice());
        holder.iconImageView.setImageResource(service.getIconResId()); // Set ikon
        holder.itemView.setOnClickListener(v -> listener.onServiceClick(service));
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public static class ServiceViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView priceTextView;
        ImageView iconImageView; // Tambahkan ImageView

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.serviceNameTextView);
            priceTextView = itemView.findViewById(R.id.servicePriceTextView);
            iconImageView = itemView.findViewById(R.id.serviceIconImageView); // Inisialisasi ImageView
        }
    }
}
