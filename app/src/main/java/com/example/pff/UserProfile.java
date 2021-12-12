package com.example.pff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {
    ImageView exit;
    Button saveChanges;
    TextView name, surname;
    EditText editTextname, editTextsurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        saveChanges = findViewById(R.id.btnSaveChanges);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = findViewById(R.id.txtName);
                surname = findViewById(R.id.txtSurname);

                editTextname = findViewById(R.id.editName);
                editTextsurname = findViewById(R.id.editSurname);

                if (editTextname.getText().toString() != null || editTextsurname.getText().toString() != null) {
                    name.setText(editTextname.getText().toString());
                    System.out.println(editTextname.getText().toString());
                    surname.setText(editTextsurname.getText().toString());
                }

            }
        });


        exit = findViewById(R.id.btnExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}