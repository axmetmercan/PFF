package com.example.pff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FilterActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        ArrayList<String> categoriesList;

        categoriesList = new ArrayList<>();
        categoriesList.add("Ahmet");
        categoriesList.add("Ahmet");
        categoriesList.add("Ahmet");
        categoriesList.add("Ahmet");
        categoriesList.add("Ahmet");
        categoriesList.add("Ahmet");
        categoriesList.add("Ahmet");
        categoriesList.add("Ahmet");
        categoriesList.add("Ahmet");
        categoriesList.add("Ahmet");


        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, categoriesList);

        listView = (ListView) findViewById(R.id.categoriesListView);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(FilterActivity.this, "Current Category is "+categoriesList.get(i), Toast.LENGTH_LONG).show();
            }
        });

    }
}