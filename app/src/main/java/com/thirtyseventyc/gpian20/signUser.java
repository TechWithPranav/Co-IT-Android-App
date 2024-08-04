package com.thirtyseventyc.gpian20;

import static android.content.ContentValues.TAG;
import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.gpian20.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class signUser extends AppCompatActivity {
    CardView mainSign;
    EditText uName;
    EditText passMain;
    EditText passConfirm;
    EditText division;
    EditText enNumber;

    EditText name;
    FirebaseAuth mAuth;
    Map<String, String> thisAcc = new HashMap<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_user);
        statusbarcolor();


        mainSign = findViewById(R.id.saveEdt);
        uName = findViewById(R.id.studentEmailSignin);
        passMain = findViewById(R.id.studentPassSignin);
        passConfirm = findViewById(R.id.studentCPassSignin);
        name = findViewById(R.id.edtName);
        division = findViewById(R.id.edtDiv);
        enNumber = findViewById(R.id.edtEnroll);
        mAuth = FirebaseAuth.getInstance();

        mainSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signNewUserAuth();
                addUsertoDb();
            }
        });
    }
    public void addUsertoDb() {


        thisAcc.put("email", uName.getText().toString());
        thisAcc.put("password", passMain.getText().toString());
        thisAcc.put("name", name.getText().toString());
        thisAcc.put("enrollment", enNumber.getText().toString());
        thisAcc.put("division", division.getText().toString());

        Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

        db.collection("Students").document(uName.getText().toString())
                .set(thisAcc)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "NOt Done", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "get failed with for dp");

                    }
                });



    }


    public void signNewUserAuth(){
        Intent toMain = new Intent(getApplicationContext(), Initial.class);

        String uNameL = uName.getText().toString();
        String passF = passMain.getText().toString();
        String passS = passConfirm.getText().toString();

        if (passF.length() < 6) {
            Toast.makeText(getApplicationContext(), "Nakli Pass", LENGTH_SHORT).show();
        } else {

            try {
                if (passF.equals(passS)) {
                    // Toast t1 = Toast.makeText(getApplicationContext(), "UserName : " + uNameL + "
                    // | Password : " + passF, Toast.LENGTH_SHORT);
                    // t1.show();

                    mAuth.createUserWithEmailAndPassword(uNameL, passF)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        startActivity(toMain);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());

                                        Toast.makeText(getApplicationContext(),
                                                task.getException().getMessage().toString(),
                                                LENGTH_SHORT).show();
                                        // updateUI(null);
                                    }
                                }
                            });
                } else {
                    Toast t2 = Toast.makeText(getApplicationContext(), "Passwords not Matched", LENGTH_SHORT);
                    t2.show();
                }

            } catch (Exception ae) {

                Toast t2 = Toast.makeText(getApplicationContext(), ae.getMessage(), LENGTH_SHORT);
                t2.show();
            }

        }
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