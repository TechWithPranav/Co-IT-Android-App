package com.thirtyseventyc.gpian20;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.gpian20.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.annotations.NonNull;

public class loginUser extends AppCompatActivity {
    EditText userMail;
    EditText userPass;
    EditText vCode;

    RadioButton isTeacher;
    RadioButton isStudent;
    CardView signUserUp;
    FirebaseAuth mAuth;
    Boolean isYesTeacher = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        statusbarcolor();

        signUserUp = findViewById(R.id.saveEdt);
        signUserUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signTheUser();
            }
        });


    }

    private void statusbarcolor() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            getWindow().setStatusBarColor(getResources().getColor(R.color.lighyB, this.getTheme()));
        } else if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.lighyB));

        }
    }


    private void signTheUser() {

        userMail = findViewById(R.id.studentEmailSignin);
        userPass = findViewById(R.id.studentPassSignin);
        vCode = findViewById(R.id.secCode);
        isTeacher = findViewById(R.id.redTeacher);
        isStudent = findViewById(R.id.redStudent);
        signUserUp = findViewById(R.id.saveEdt);
        isTeacher = findViewById(R.id.redTeacher);
        isStudent = findViewById(R.id.redStudent);
        vCode = findViewById(R.id.secCode);

        String userMaill = userMail.getText().toString();
        String userPasss = userPass.getText().toString();
        String vCodee = vCode.getText().toString();

        mAuth = FirebaseAuth.getInstance();


        if (isTeacher.isChecked())
        {
            if (vCodee.equals("aa"))
            {
                mAuth.signInWithEmailAndPassword(userMaill, userPasss)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent toInitial = new Intent(getApplicationContext(), Initila_Teacher.class);
                                    toInitial.putExtra("isTeacher", "yes");
                                    startActivity(toInitial);
                                    //  updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(loginUser.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }else {
                Toast.makeText(getApplicationContext(), "Please Enter Valid Code", Toast.LENGTH_SHORT).show();
            }

        } else if (isStudent.isChecked()) {

            mAuth.signInWithEmailAndPassword(userMaill, userPasss)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent toInitial = new Intent(getApplicationContext(), Initial.class);
                                startActivity(toInitial);
                                //  updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(loginUser.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else
        {

            Toast.makeText(getApplicationContext(), "Please Select the Position", Toast.LENGTH_SHORT).show();
    }




}}