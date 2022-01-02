package com.example.pff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.resources.TextAppearance;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class PetDetailsActivity extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    String name, category, color, age, sex, type, imgUrl, phone, ownerName;
    TextView tname, tcategory, tcolor, tage, tsex, ttype;
    ImageView imageView;
    Button sendMessage;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        firebaseFirestore = FirebaseFirestore.getInstance();

        sendMessage = findViewById(R.id.btnContactOwner);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, Object> postPet = new HashMap<>();
                postPet.put("downloadUrl", imgUrl);
                postPet.put("username", user.getDisplayName());
                postPet.put("petOwner", ownerName);
                //postPet.put("userPhotoUrl", user.getPhotoUrl());
                postPet.put("petName", name);
                postPet.put("petAge", age);
                postPet.put("contactNumber", phone);

                postPet.put("date", FieldValue.serverTimestamp());

                firebaseFirestore.collection("MessagedUsers").add(postPet).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(@NonNull DocumentReference documentReference) {

                        System.out.println("Gönderildi");

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        System.out.println("Gönderilemiyor");
                    }
                });







                Intent intent = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:"+phone));
//                intent.setData();  // This ensures only SMS apps respond
                intent.putExtra("sms_body", "Hello "+ ownerName +", i am interested in your pet.");
//                intent.putExtra(Intent.EXTRA_STREAM, attachment);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });

        getSetdata();
    }

    private void getSetdata() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            name = bundle.getString("name");
            category = bundle.getString("category");
            color = bundle.getString("color");
            age = bundle.getString("age");
            sex = bundle.getString("sex");
            type = bundle.getString("type");
            imgUrl = bundle.getString("imgUrl");
            phone = bundle.getString("phone");
            ownerName = bundle.getString("ownerName");
        }

        tname = findViewById(R.id.petNameDetails);
        tname.setText(tname.getText() + " " + name);

        tcategory = findViewById(R.id.petCategoryDetails);
        tcategory.setText(category);

        tcolor = findViewById(R.id.petColorDetails);
        tcolor.setText(color);

        tage = findViewById(R.id.petAgeDetails);
        tage.setText(age);

        tsex = findViewById(R.id.petSexDetails);
        tsex.setText(sex);

        ttype = findViewById(R.id.petTypeDetails);
        ttype.setText(type);

        imageView = findViewById(R.id.petImageDetail);

        Picasso.get().load(imgUrl).into(imageView);

    }
}