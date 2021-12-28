package com.example.pff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.resources.TextAppearance;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class PetDetailsActivity extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    String name, category, color, age, sex, type, imgUrl;
    TextView tname, tcategory, tcolor, tage, tsex, ttype;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        firebaseFirestore = FirebaseFirestore.getInstance();

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