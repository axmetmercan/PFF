package com.example.pff;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class addNewAnimal extends Fragment {
    ImageView petImage;
    TextView name, sex;
    Number age;
    ArrayList<String> color;
    ArrayList<String> category;
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_animal, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        fillColor();
        fillCategory();
        setSpinners(view);

        petImage = view.findViewById(R.id.imgPetPic);
        petImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    startActivityForResult(takePictureIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                } catch (ActivityNotFoundException e) {
                    // display error state to the user
                    Toast.makeText(getActivity(), "Camera is Not Available", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                petImage.setImageBitmap(bp);
            }else if (resultCode == RESULT_CANCELED)
                Toast.makeText(getActivity(),"Cancelled", Toast.LENGTH_LONG).show();
        }
    }

    private void setSpinners(View view) {
        Spinner colorSpinner = view.findViewById(R.id.spnColor);
        Spinner categorySpinner = view.findViewById(R.id.spnCategory);

        ArrayAdapter<String> colors = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, color);
        colorSpinner.setAdapter(colors);
        ArrayAdapter<String> categories = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, category);
        categorySpinner.setAdapter(categories);

    }

    private void fillCategory() {
        category = new ArrayList<>();
        category.add("Dog");
        category.add("Cat");
        category.add("Horse");
        category.add("Ferret");
        category.add("Squirrel");
        category.add("Rabbit");
        category.add("Hamster");
        category.add("Bird");

    }

    private void fillColor() {
        color = new ArrayList<>();
        color.add("White");
        color.add("Chocolate");
        color.add("Caramel");
        color.add("Black");
        color.add("Dotted");
        color.add("Coffee");
        color.add("Pink");
        color.add("Orange");
        color.add("Yellow");

    }


}