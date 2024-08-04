package com.thirtyseventyc.gpian20;

import static android.content.ContentValues.TAG;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.gpian20.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class editProfile extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    String enrollment, name, division;
    TextView emailT;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText nameView, divView, enrollView;
    CardView saveEdt;
    Map<String, String> thisAcc = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        emailT = findViewById(R.id.emailTextedt);
        nameView = findViewById(R.id.edtName);
        divView = findViewById(R.id.edtDiv);
        enrollView = findViewById(R.id.edtEnroll);
        saveEdt = findViewById(R.id.saveEdt);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address
            String email = user.getEmail();
            emailT.setText(email);

            DocumentReference docRef = db.collection("Students").document(email);

            try {

                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful())
                        {
                            DocumentSnapshot document = task.getResult();
                            enrollment = document.getString("enrollment");
                            name = document.getString("name");
                            division = document.getString("division");

                            nameView.setText(name);
                            divView.setText(division);
                            enrollView.setText(enrollment);


                        }
                        else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
            }catch (Exception e)
            {
                Toast.makeText(getApplicationContext(), "Some Error Occured", Toast.LENGTH_SHORT).show();
            }

        }


        saveEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                thisAcc.put("name", nameView.getText().toString());
                thisAcc.put("enrollment", enrollView.getText().toString());
                thisAcc.put("division", divView.getText().toString());

                db.collection("Students").document(emailT.getText().toString())
                        .set(thisAcc)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "success");
//                                Intent toHome = new Intent(getApplicationContext(), Initila_Teacher.class);
//                                startActivity(toHome);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "NOt Done", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "get failed with for dp");

                            }
                        });

            }
        });



        save();

    }

    public void save(){
        SharedPreferences sharedPref = getSharedPreferences("MyData",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name",name);
//            editor.putString("enrollment",enrollment);
//            editor.putString("division",division);
//            editor.putString("email",email);
        //  Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();

        editor.commit();

        Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT);
    }
}