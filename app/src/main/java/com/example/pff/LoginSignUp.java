package com.example.pff;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class LoginSignUp extends AppCompatActivity {
    private Button signUp, logIn;
    private FirebaseAuth mAuth;
    private ImageView appLogo;
    private EditText email, pass;
    private BottomNavigationView bottomNavigationView;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(LoginSignUp.this, LoginSignUp.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Please Sign in or Login First", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("email", email.getText().toString());
        outState.putString("password", pass.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String usermail = savedInstanceState.getString("email");
        String password = savedInstanceState.getString("password");

        email.setText(usermail);
        pass.setText(password);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);
        mAuth = FirebaseAuth.getInstance();

        appLogo = findViewById(R.id.appLogo);


        email = (EditText) findViewById(R.id.txtEmail);
        email.getText().toString();


        pass = (EditText) findViewById(R.id.txtPassword);
        pass.getText().toString();

        signUp = findViewById(R.id.btnSignUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp = findViewById(R.id.btnSignUp);


                if (email.getText().toString() != "" && pass.getText().toString() != "") {

                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(@NonNull AuthResult authResult) {

//                            bottomNavigationView = findViewById(R.id.bottomNavigationView);
//                            bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
//                            getSupportFragmentManager().beginTransaction().replace(R.id.recyclerView, new homePage()).commit();
                            Toast.makeText(LoginSignUp.this, "New subscription has been started", Toast.LENGTH_SHORT).show();


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            System.out.println("Burda Hata yok");

                            Toast.makeText(LoginSignUp.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    {

                    }
                }


            }
        });

        logIn = findViewById(R.id.btnLogin);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String em = email.getText().toString();
                String password = pass.getText().toString();
                mAuth.signInWithEmailAndPassword(em, password)
                        .addOnCompleteListener(LoginSignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();


                                    if (user.getDisplayName() == null || user.getDisplayName().equals("")) {
                                        Intent intent1 = new Intent(LoginSignUp.this, UserProfile.class);
                                        startActivity(intent1);
                                    }
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginSignUp.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }

        });


    }


}