package com.thirtyseventyc.gpian20;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.gpian20.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class fragProfile extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String emaiil = "samay@gmail.com";
    TextView logout;
    TextView emailView, nameView, divView, enrollView;

    String enrollment;
    CardView editPro;
    String name;
    String division;
    public fragProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     //   return inflater.inflate(R.layout.fragment_frag_profile, container, false);
        View view = inflater.inflate(R.layout.fragment_frag_profile, container, false);

        logout = view.findViewById(R.id.logoutText);
        emailView = view.findViewById(R.id.emailText);
        nameView = view.findViewById(R.id.nameText);
        enrollView = view.findViewById(R.id.enrollText);
        divView = view.findViewById(R.id.divText);
        editPro = view.findViewById(R.id.editPro);

        editPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toEdit = new Intent(getContext(), editProfile.class);
                startActivity(toEdit);
            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent toOptionScreen = new Intent(getContext(), optionScrn.class);
                Toast.makeText(getContext(), "Logging Out...", Toast.LENGTH_SHORT).show();
                startActivity(toOptionScreen);
            }
        });






        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address
            String email = user.getEmail();
            emailView.setText(email);

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
                Toast.makeText(getContext(), "Some Error Occured", Toast.LENGTH_SHORT).show();
            }

        }



//
//        public void save(){
//            SharedPreferences sharedPref = getSharedPreferences("MyData",MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPref.edit();
//            editor.putString("name",name);
//            editor.commit();
//
//            Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT);
//        }
//

        return view;
    }

    @Override
    public void onStart() {


        super.onStart();
        Toast.makeText(getContext(), "Profile", Toast.LENGTH_SHORT).show();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address
            String email = user.getEmail();
            emailView.setText(email);

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
                Toast.makeText(getContext(), "Some Error Occured", Toast.LENGTH_SHORT).show();
            }

        }


    }
}