package com.thirtyseventyc.gpian20;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gpian20.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class a6_notes_update extends AppCompatActivity {

    Intent data;

    EditText edit_notes_title,edit_notes_sub_title,edit_notes_url,edit_notes_main_data;
    FloatingActionButton edit_done_btn_for_edit;

    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a6_activity_update);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("THIRD YEAR");
        }


        // initialize variables

        edit_notes_title = findViewById(R.id.edit_notes_title);
        edit_notes_sub_title = findViewById(R.id.edit_notes_sub_title);
        edit_notes_url = findViewById(R.id.edit_notes_url);
        edit_notes_main_data = findViewById(R.id.edit_notes_main_data);
        edit_done_btn_for_edit = findViewById(R.id.edit_done_btn_for_edit);


        // seting the values inside editext so that we can edit that data

        data = getIntent();


        String noteTitle=data.getStringExtra("title");
        String noteSubTitle=data.getStringExtra("subtitle");
        String noteMainData=data.getStringExtra("notes_main_data");
        String noteUrl=data.getStringExtra("notes_url");

        edit_notes_title.setText(noteTitle);
        edit_notes_sub_title.setText(noteSubTitle);
        edit_notes_url.setText(noteUrl);
        edit_notes_main_data.setText(noteMainData);


        // for inserting updated data

        firebaseFirestore = FirebaseFirestore.getInstance();

        edit_done_btn_for_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String newTitle = edit_notes_title.getText().toString();
                String newSubTitle = edit_notes_sub_title.getText().toString();
                String newUrl = edit_notes_url.getText().toString();
                String newMainNote = edit_notes_main_data.getText().toString();

                if(newTitle.isEmpty() || newSubTitle.isEmpty() || newUrl.isEmpty() || newMainNote.isEmpty()){
                    Toast.makeText(a6_notes_update.this, "Please Enter All fields Properly", Toast.LENGTH_SHORT).show();

                }
                else {
                    Intent intent = getIntent();
                    String data2 = intent.getStringExtra("key5");
                    DocumentReference documentReference = firebaseFirestore.collection(data2).document(data.getStringExtra("noteId"));
                    Map<String,Object> note = new HashMap<>();
                    note.put("Title",newTitle);
                    note.put("SubTitle",newSubTitle);
                    note.put("Url",newUrl);
                    note.put("Notes",newMainNote);

                    documentReference.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(a6_notes_update.this, "Notes updated Successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(a6_notes_update.this, a3_main_notes_all.class);
                            i.putExtra("data", data2);
                            startActivity(i);
                            return;
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(a6_notes_update.this, "Fail to Update Notes", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    });
                }



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