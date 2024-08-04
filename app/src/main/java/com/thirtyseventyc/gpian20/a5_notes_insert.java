package com.thirtyseventyc.gpian20;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gpian20.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class a5_notes_insert extends AppCompatActivity {

    EditText title,subtitle,main_notes,notes_url;
    FloatingActionButton done_insert;

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a5_activity_insert);
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("THIRD YEAR");
        }




        title = findViewById(R.id.notes_title);
        subtitle = findViewById(R.id.notes_sub_title);
        main_notes = findViewById(R.id.notes_main_data);
        notes_url = findViewById(R.id.notes_url);
        done_insert = findViewById(R.id.done_btn_for_insert);


        done_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getTitle = title.getText().toString();
                String getSubtitle = subtitle.getText().toString();
                String getmMainNotes = main_notes.getText().toString();
                String getNotes_url = notes_url.getText().toString();

                if(getTitle.isEmpty() || getSubtitle.isEmpty() || getmMainNotes.isEmpty() || getNotes_url.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Both field are required",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = getIntent();
                    String data = intent.getStringExtra("key3"); // retrieve data using key

//                    DocumentReference documentReference = firebaseFirestore.collection("notes").document(firebaseUser.getUid()).collection("mynotes").document();
                    DocumentReference documentReference = firebaseFirestore.collection(data).document();

                    Map<String,Object> note = new HashMap<>();
                    note.put("Title",getTitle);
                    note.put("SubTitle",getSubtitle);
                    note.put("Url",getNotes_url);
                    note.put("Notes",getmMainNotes);

                    documentReference.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(),"Notes Created Successfully....",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(a5_notes_insert.this, a3_main_notes_all.class);
                            intent.putExtra("data", data);
                            startActivity(intent);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Failed to create notes....",Toast.LENGTH_SHORT).show();
                        }
                    });
                }




//                Toast.makeText(t_java_1_insert.this,"Notes Created Successfully",Toast.LENGTH_SHORT).show();

            }
        });



    }


    // to handle back pressed event of action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This will take the user back to the previous activity
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}