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
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class messages extends Fragment {

    private ArrayList<Message> messageArrayList;
    private RecyclerView recyclerView;
    private messagesRecyclerView messagesRecyclerView;
    private FirebaseFirestore firebaseFirestore;


    public messages() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        setServices(view);
        getData();
        messagesRecyclerView.notifyDataSetChanged();


    }

    private void fillTheArray() {
//        for (int i = 0; i<20; i++){
//           messageArrayList.add(new Message("Hasan", "Cakil","12"));
//           messageArrayList.add(new Message("Hasan", "Cakil","12"));
//           System.out.println(messageArrayList.size());
//        }
    }


    private void getData() {
        firebaseFirestore.collection("MessagedUsers").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(getContext(), error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

                if (value != null) {
                    for (DocumentSnapshot documentSnapshot : value.getDocuments()) {
                        Map<String, Object> data = documentSnapshot.getData();


                        String petImageUrl = (String) data.get("downloadUrl");
                        String petSex = (String) data.get("petSex");
                        String petName = (String) data.get("petName");
                        String petAge = (String) data.get("petAge");
                        String petCategory = (String) data.get("petCategory");
                        String petOwnerPhone = (String) data.get("contactNumber");
                        String petColor = (String) data.get("petColor");
                        String petType = (String) data.get("type");
                        String ownerName = (String) data.get("username");


                        messageArrayList.add(new Message("Owner: " + ownerName, "Pet: " + petName, petImageUrl, petOwnerPhone));

                        System.out.println(petName);
                    }
                    messagesRecyclerView.notifyDataSetChanged();
                }

            }
        });
    }

    private void setServices(View view) {
        recyclerView = view.findViewById(R.id.recyclerView2);
        messageArrayList = new ArrayList<>();
        messagesRecyclerView = new messagesRecyclerView(messageArrayList);
        recyclerView.setAdapter(messagesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

}