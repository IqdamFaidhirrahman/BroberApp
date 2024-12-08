// ServiceListFragment.java
package com.example.broberapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceListFragment extends Fragment {

    private EditText searchBar;
    private ServiceAdapter adapter;
    private List<Service> fullServiceList; // List penuh untuk referensi asli
    private List<Service> filteredServiceList; // List yang disaring untuk ditampilkan

    public interface OnServiceSelectedListener {
        void onServiceSelected(Service service);
    }

    private OnServiceSelectedListener listener;
    
    public ServiceListFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_list, container, false);

        searchBar = view.findViewById(R.id.searchBar);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fullServiceList = getServiceList();
        filteredServiceList = new ArrayList<>(fullServiceList); // Mulai dengan semua layanan

        adapter = new ServiceAdapter(filteredServiceList, service -> {
            // Navigasi ke ServiceDetailFragment saat layanan diklik
            if (getActivity() instanceof OnServiceSelectedListener) {
                listener = (OnServiceSelectedListener) getActivity();
                listener.onServiceSelected(service);
            }
        });

        recyclerView.setAdapter(adapter);

        // Tambahkan TextWatcher untuk menangani pencarian
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                filterServices(s.toString());
            }
        });

        return view;
    }

    private void filterServices(String query) {
        filteredServiceList.clear();
        if (query.isEmpty()) {
            // Jika pencarian kosong, tampilkan semua layanan
            filteredServiceList.addAll(fullServiceList);
        } else {
            // Filter layanan berdasarkan nama
            for (Service service : fullServiceList) {
                if (service.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredServiceList.add(service);
                }
            }
        }
        adapter.notifyDataSetChanged(); // Perbarui tampilan
    }

    private List<Service> getServiceList() {
        List<Service> services = Arrays.asList(
                new Service("Haircut", "Layanan potong rambut sesuai dengan gaya yang diinginkan, " +
                        "baik itu gaya modern, klasik, atau potongan terbaru yang sedang tren. " +
                        "Layanan ini dapat mencakup konsultasi gaya rambut yang sesuai dengan bentuk wajah dan preferensi pribadi. " +
                        "Contoh: “Potongan Fade”, “Undercut”, atau “Pompadour”. ", 15.0, R.drawable.haircut),
                new Service("Shave", "Layanan cukur atau perawatan jenggot dan kumis, " +
                        "baik secara bersih maupun hanya merapikan bentuknya. " +
                        "Layanan ini termasuk pencukuran dengan pisau cukur tajam dan disertai dengan minyak atau balsem khusus untuk kenyamanan kulit." +
                        "Contoh: “Full Shave”, “Beard Trim”, atau “Goatee Styling”. ", 10.0, R.drawable.shave),
                new Service("Haircut & Shave", "Kombinasi antara layanan potong rambut dan cukur jenggot atau kumis dalam satu paket lengkap. " +
                        "Contoh: “Executive Cut & Shave” yang memberikan layanan lengkap dari ujung rambut hingga wajah, " +
                        "cocok untuk tampilan yang bersih dan profesional.", 20.0, R.drawable.haircut_shave),
                new Service("Hair Color", "Layanan pewarnaan rambut baik untuk menutupi uban, memberikan efek highlight, " +
                        "atau mewarnai seluruh rambut dengan warna-warna fashion. " +
                        "Pewarnaan rambut dilakukan dengan produk berkualitas dan pilihan warna yang sesuai dengan tren. " +
                        "Contoh: “Full Hair Color”, “Highlight”, atau “Grey Coverage”.", 30.0, R.drawable.hair_color),
                new Service("Massage", "Layanan pijat kepala, bahu, atau tangan yang membantu relaksasi dan melancarkan peredaran darah. " +
                        "Contoh: “Head & Shoulder Massage” yang bisa menenangkan tubuh dan pikiran setelah menjalani aktivitas yang padat.", 25.0, R.drawable.massage),
                new Service("Facial", "Layanan perawatan wajah yang membersihkan kulit wajah dari kotoran, minyak, dan sel kulit mati untuk tampilan yang lebih segar. " +
                        "Contoh: “Deep Clean Facial” atau “Anti-Aging Facial” yang biasanya melibatkan pemakaian masker khusus dan krim pelembab.", 18.0, R.drawable.facial),
                new Service("Hair Tattoo", "Layanan seni desain pada rambut dengan mencukur pola tertentu, " +
                        "menciptakan desain seperti garis, logo, atau bentuk lain yang unik. " +
                        "Cocok untuk mereka yang ingin tampilan unik dan personal pada rambut. " +
                        "Contoh: “Geometric Pattern” atau “Tribal Design”. ", 40.0, R.drawable.hair_tattoo),
                new Service("Styling Pomade", "Layanan penataan rambut menggunakan pomade untuk menciptakan gaya tertentu, " +
                        "seperti gaya klimis atau gaya yang lebih tahan lama. " +
                        "Contoh: “Classic Pomade Style” atau “Textured Look” dengan pomade berbahan dasar minyak atau air.", 8.0, R.drawable.pomade),
                new Service("Cuci", "Layanan pencucian rambut dengan shampoo dan kondisioner khusus untuk menyegarkan rambut dan kulit kepala. " +
                        "Contoh: “Refreshing Hairwash” atau “Anti-Dandruff Wash” yang menawarkan pengalaman bersih, nyaman, dan wangi pada rambut.", 5.0, R.drawable.wash)
        );
        Log.d("ServiceListFragment", "Loaded services: " + services.size());
        return services;
    }
}
