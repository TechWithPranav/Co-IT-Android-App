package com.thirtyseventyc.gpian20;

import static android.content.ContentValues.TAG;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.gpian20.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Initial extends AppCompatActivity {
    String name, enrollment, division, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BottomNavigationView bottomNavigationView;
        setContentView(R.layout.activity_initial);
        statusbarcolor();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        getUInfo();

        bottomNavigationView = findViewById(R.id.Bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new fragHome()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.nav_home:
                        fragment = new fragHome();
                        break;

                    case R.id.nav_chat:
                        fragment = new waste_frag();
//                        Intent intent = new Intent(getApplicationContext(), MasalaChat.class);
//                        startActivity(intent);
                        break;
                    case R.id.nav_notes:
                        fragment = new fragNotes();
                        break;
                    case R.id.nav_profile:
                        fragment = new fragProfile();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();
                return true;
            }
        });

    }

    public void statusbarcolor()
    {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            getWindow().setStatusBarColor(getResources().getColor(R.color.browny, this.getTheme()));
        } else if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.browny));

        }
    }
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void getUInfo()
    {


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address
             email = user.getEmail();
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
                            save();
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




        DocumentReference docRef = db.collection("Students").document(email);

        try {

            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful())
                    {
                        DocumentSnapshot document = task.getResult();
                        name = document.getString("name");
                        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();

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