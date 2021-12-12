package com.example.pff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new homePage()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {

            Fragment fragment = null;

                switch ((menuitem.getItemId()))
                {
                    case R.id.homePage:
                        fragment = new homePage();
                        break;

                    case R.id.addNewAnimal:
                        fragment = new addNewAnimal();
                        break;

                    case R.id.messages:
                        fragment = new messages();
                        break;


                    case R.id.myAdvertisements:
                        fragment = new myAdvertisements();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).commit();


            return true;
        }
    };



}