package com.thirtyseventyc.gpian20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.gpian20.R;

public class walk1 extends AppCompatActivity {
    CardView toWalk2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk1);
        toWalk2 = findViewById(R.id.waste_b);

        toWalk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextWalk = new Intent(getApplicationContext(), walk2.class);
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