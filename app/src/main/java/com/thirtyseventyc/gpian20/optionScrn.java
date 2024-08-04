package com.thirtyseventyc.gpian20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.gpian20.R;

public class optionScrn extends AppCompatActivity {

    CardView toLog;
    CardView toSign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_scrn);

        toLog = findViewById(R.id.logUserF);
        toSign = findViewById(R.id.saveEdt);


        toLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextWalk = new Intent(getApplicationContext(), loginUser.class);
                startActivity(nextWalk);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

            }
        });

        toSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextWalk = new Intent(getApplicationContext(), signUser.class);
                startActivity(nextWalk);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

            }
        });

        statusbarcolor();


    }

    public void statusbarcolor()
    {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            getWindow().setStatusBarColor(getResources().getColor(R.color.lighyB, this.getTheme()));
        } else if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.lighyB));

        }
    }

}