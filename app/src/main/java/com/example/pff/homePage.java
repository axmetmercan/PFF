package com.example.pff;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

public class homePage extends Fragment {

    private ArrayList<Pet> petArrayList;
    private RecyclerView recyclerView;
    private petRecyclerView petRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton userButton = (ImageButton) view.findViewById(R.id.btnUser);
        ImageButton filterButton = (ImageButton) view.findViewById(R.id.btnFilter);


        setservices(view);
        fillthearray();
        petRecyclerView.notifyDataSetChanged();

//
//        recyclerView = view.findViewById(R.id.recyleView1);
//        petArrayList = new ArrayList<>();
//        petRecyclerView = new petRecyclerView(petArrayList);
//        recyclerView.setAdapter(petRecyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FilterActivity.class);
                startActivity(intent);
            }
        });



        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UserProfile.class);
                startActivity(intent);
            }
        });


    }

    private void fillthearray() {

        for (int i = 0; i<999999; i++){
            petArrayList.add(new Pet("Ali", "pink", "Dog", 2, "female"));

        }

    }

    private void setservices(View view) {
        recyclerView = view.findViewById(R.id.recyleView1);
        petArrayList = new ArrayList<>();
        petRecyclerView = new petRecyclerView(petArrayList);
        recyclerView.setAdapter(petRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    }
}