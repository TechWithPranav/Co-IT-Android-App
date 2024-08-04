package com.thirtyseventyc.gpian20;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gpian20.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    TextView toTemp;
    ProgressBar pg;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toTemp = findViewById(R.id.textView);
        pg = findViewById(R.id.progressBar);
        pg.setProgress(0);
        Intent intent = new Intent(getApplicationContext(), optionScrn.class);

        CountDownTimer countDownTimer = new CountDownTimer(1000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                int current = pg.getProgress() + 3;
                pg.setProgress(current);
            }

            @Override
            public void onFinish() {
                mAuth = FirebaseAuth.getInstance();

                FirebaseUser currentUser = mAuth.getCurrentUser();
                if(currentUser != null){
                    Intent toInitial = new Intent(getApplicationContext(), Initial.class);
                    startActivity(toInitial);
                }else {
                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT);
                startActivity(intent);}
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

            }
        }.start();

        toTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), optionScrn.class);
                startActivity(intent);
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