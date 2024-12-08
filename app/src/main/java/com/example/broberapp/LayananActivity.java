// LayananActivity.java
package com.example.broberapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class LayananActivity extends AppCompatActivity implements ServiceListFragment.OnServiceSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layanan);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_list_container, new ServiceListFragment())
                    .commit();
            Log.d("MainActivity", "ServiceListFragment added to fragment_list_container");
        }
    }

    @Override
    public void onServiceSelected(Service service) {
        ServiceDetailFragment detailFragment = ServiceDetailFragment.newInstance(service);

        // Tampilkan detail di bawah daftar
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_detail_container, detailFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();

        // Pastikan detail terlihat
        findViewById(R.id.fragment_detail_container).setVisibility(View.VISIBLE);
    }
}
