// ServiceDetailFragment.java
package com.example.broberapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ServiceDetailFragment extends Fragment {

    private static final String ARG_NAME = "name";
    private static final String ARG_DESC = "description";
    private static final String ARG_PRICE = "price";

    public static ServiceDetailFragment newInstance(Service service) {
        ServiceDetailFragment fragment = new ServiceDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, service.getName());
        args.putString(ARG_DESC, service.getDescription());
        args.putDouble(ARG_PRICE, service.getPrice());
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_detail, container, false);

        TextView nameTextView = view.findViewById(R.id.nameTextView);
        TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);
        TextView priceTextView = view.findViewById(R.id.priceTextView);
        Button bookButton = view.findViewById(R.id.bookButton);

        if (getArguments() != null) {
            nameTextView.setText(getArguments().getString(ARG_NAME));
            descriptionTextView.setText(getArguments().getString(ARG_DESC));
            priceTextView.setText("$" + getArguments().getDouble(ARG_PRICE));
        }

        bookButton.setOnClickListener(v ->
                Toast.makeText(getContext(), "Booking for " + nameTextView.getText(), Toast.LENGTH_SHORT).show()
        );
        return view;
    }
}
