package com.example.pff;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class myAdvertisements extends Fragment {

    private ArrayList<PublishedAdvertisements> publishedAdvertisementsArrayList;
    private RecyclerView recyclerView;
    private publishedAdvertismentsRecyclerView publishedAdvertismentsRecyclerView;

    public myAdvertisements() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_advertisements, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setServices(view);
        filltheArray();
    }

    private void setServices(View view) {

        recyclerView = view.findViewById(R.id.recyclerView3);
        publishedAdvertisementsArrayList = new ArrayList<>();
        publishedAdvertismentsRecyclerView = new publishedAdvertismentsRecyclerView(publishedAdvertisementsArrayList);
        recyclerView.setAdapter(publishedAdvertismentsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void filltheArray() {

        for (int i = 0; i<20; i++){
            publishedAdvertisementsArrayList.add(new PublishedAdvertisements("Cakil","Dog"));
        }

    }
}