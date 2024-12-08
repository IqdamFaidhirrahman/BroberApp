package com.example.broberapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.ViewHolder> {

    private List<String> paymentMethods;
    private OnPaymentMethodClickListener listener;

    public interface OnPaymentMethodClickListener {
        void onClick(String method);
    }

    public PaymentMethodAdapter(List<String> paymentMethods, OnPaymentMethodClickListener listener) {
        this.paymentMethods = paymentMethods;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment_method, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String method = paymentMethods.get(position);
        holder.bind(method, listener);
    }

    @Override
    public int getItemCount() {
        return paymentMethods.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPaymentName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvPaymentName = itemView.findViewById(R.id.tvPaymentName);
        }

        public void bind(String method, OnPaymentMethodClickListener listener) {
            tvPaymentName.setText(method);
            itemView.setOnClickListener(v -> listener.onClick(method));
        }
    }
}
