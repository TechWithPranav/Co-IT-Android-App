package com.thirtyseventyc.gpian20;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.gpian20.R;


public class a4_notes_detail extends AppCompatActivity {



    private TextView notes_title_taken_db,notes_subTitle_taken_db,notes_main_taken_db,notes_link_taken_db;
    private  WebView webView_link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a4_activity_notes_detail);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("THIRD YEAR");
        }


        // initialize variables --------------------------------------------

        notes_title_taken_db = findViewById(R.id.notes_title_taken_db);
        notes_subTitle_taken_db = findViewById(R.id.notes_subTitle_taken_db);
        notes_main_taken_db = findViewById(R.id.notes_main_taken_db);
        notes_link_taken_db = findViewById(R.id.notes_link_taken_db);
        webView_link = findViewById(R.id.webView_link);


        Intent data = getIntent();

        notes_title_taken_db.setText(data.getStringExtra("title"));
        notes_subTitle_taken_db.setText(data.getStringExtra("subtitle"));
        notes_main_taken_db.setText(data.getStringExtra("notes_main_data"));
        notes_link_taken_db.setText(data.getStringExtra("notes_url"));

//        notes_link_taken_db.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String text_link = notes_link_taken_db.getText().toString();
//
//                // Check if the URL is a Google Drive link
//                if (text_link.contains("drive.google.com")) {
//                    // Replace the URL with a modified URL that forces the PDF file to be downloaded
//                    text_link = text_link.replace("/view", "/preview");
//                    webView_link.loadUrl(text_link);
//                    webView_link.setVisibility(View.INVISIBLE);
//
//
//                }
//                else{
//
//
//                    // Load modified URL in WebView
//                    webView_link.setVisibility(View.VISIBLE);
//                    webView_link.loadUrl(text_link);
//
//
//                }
//
//
//
//
//            }
//        });
        notes_link_taken_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text_link = notes_link_taken_db.getText().toString();
                // Check if the URL is a Google Drive link
                if (text_link.contains("drive.google.com")) {
                    // Replace the URL with a modified URL that forces the PDF file to be downloaded
                    text_link = text_link.replace("/view", "/preview");
                    webView_link.loadUrl(text_link);
                    webView_link.setVisibility(View.INVISIBLE);

                }

                else if (text_link.contains("drive.google.com/folder")) {
                    // Open the folder share link in the default web browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(text_link));
                    startActivity(intent);
                }
                else {
                    // Load modified URL in WebView
                    webView_link.setVisibility(View.VISIBLE);
                    webView_link.loadUrl(text_link);
                }
            }
        });



    }
    @Override
    public void onBackPressed() {
        // Handle back button press
        if (webView_link.getVisibility() == View.VISIBLE) {
            // If WebView is visible, hide it and go back to previous activity
            webView_link.setVisibility(View.INVISIBLE);
        } else {
            // If WebView is not visible, close this activity and go back to previous activity
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Hide the WebView when the activity resumes
        webView_link.setVisibility(View.INVISIBLE);
    }

}