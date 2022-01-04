package com.example.pff;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class UserProfile extends AppCompatActivity {
    ImageView exit;
    Button saveChanges, discardChanges, deleteAccount;
    TextView name, email;
    EditText editTextname, editTextsurname;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    boolean flag;
    private Handler mainHandler = new Handler();



    @Override
    public void onBackPressed() {
        super.onBackPressed();


        if (user.getDisplayName() == null || user.getDisplayName().isEmpty()){
            Intent intent = new Intent(UserProfile.this, LoginSignUp.class);

            startActivity(intent);
        }
        flag = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flag = true;
        setContentView(R.layout.activity_user_profile);
        name = findViewById(R.id.txtName);
        email = findViewById(R.id.txtSurname);

        editTextname = findViewById(R.id.editName);
        editTextsurname = findViewById(R.id.editSurname);

        name.setText(user.getDisplayName());
        email.setText(user.getEmail());

        deleteAccount = findViewById(R.id.btnDeleteAccount);

        discardChanges = findViewById(R.id.btnDiscard);
        CheckerThread t1 = new CheckerThread(editTextname, editTextsurname);
        t1.start();
        discardChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = false;
                finish();
            }
        });
        saveChanges = findViewById(R.id.btnSaveChanges);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = false;

                if (editTextname.getText().toString() != "" || editTextsurname.getText().toString() != "") {


                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(editTextname.getText().toString() + " " + editTextsurname.getText().toString())
                            .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                            .build();

                    name.setText(user.getDisplayName());
                    email.setText(user.getEmail());


                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "User profile updated.");
                                    }
                                }
                            });

                    finish();

                }

            }
        });


//        name.setText(user.getDisplayName());
//        surname.setText(user.getEmail());

        System.out.println("Name: " + name.getText() + "Surname: " + email.getText());

        if ((name.getText().toString().equals("") || name.getText() == null) || (email.getText().toString().equals("") || email.getText() == null)) {
            discardChanges.setClickable(false);
        }


//        if (editTextname.getText().toString().equals("") || editTextsurname.getText().toString().equals("")){
//            System.out.println("Hello world");
//            saveChanges.setClickable(false);
//        }
//        else saveChanges.setClickable(true);


        Intent intent = new Intent(UserProfile.this, LoginSignUp.class);


        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();

                user.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void unused) {
                        Toast.makeText(getApplicationContext(), "User has been succesfully deleted from system", Toast.LENGTH_LONG).show();
                        flag = false;
                        startActivity(intent);

                    }
                });

            }
        });


        exit = findViewById(R.id.btnExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = false;
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();

                Toast.makeText(getApplicationContext(), "User has been sign out successfully", Toast.LENGTH_SHORT).show();

                startActivity(intent);
                finish();
            }
        });





    }


    //Thread

    class CheckerThread extends Thread {
        EditText name, surname;

        CheckerThread(EditText name, EditText surname) {
            this.name = name;
            this.surname = surname;
        }

        @Override
        public void run() {
            while (flag)  {
//                saveChanges.setClickable(false);
//                saveChanges.setBackgroundColor(Color.RED);
//                System.out.println("thread calisiyor");
                if ((name.getText().toString().length() >= 3 && surname.getText().toString().length() >= 3)){
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            saveChanges.setClickable(true);
                            saveChanges.setBackgroundColor(Color.GREEN);
                        }
                    });
                    System.out.println("thread calisiyor true");
                }
                else if ((name.getText().toString().length() < 3 || surname.getText().toString().length() < 3)){
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            saveChanges.setClickable(false);
                            saveChanges.setBackgroundColor(Color.RED);
                        }
                    });

                    System.out.println("thread calisiyor false ");
                }

            }

        }
    }
}


