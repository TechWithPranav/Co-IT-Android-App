package com.thirtyseventyc.gpian20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.gpian20.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Initila_Teacher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BottomNavigationView bottomNavigationView;
        setContentView(R.layout.activity_initila_teacher);
        statusbarcolor();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);


        bottomNavigationView = findViewById(R.id.Bottom_navigation_t);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new fragHome()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.nav_home:
                        fragment = new fragHome();
                        break;

                    case R.id.nav_post:
                        fragment = new fragPost();
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
}