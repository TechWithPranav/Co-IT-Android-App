package com.thirtyseventyc.gpian20;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gpian20.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;













////////////////////////////GPT coode trial_1///////////////////////////////////


public class MasalaChat extends AppCompatActivity {
    private EditText userinput;
    private ImageButton sendbutton;
    private CardView imageCard;
    private LinearLayout textlayout;
    public static String sendername="";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference("messages");
    DatabaseReference messageCountRef = database.getReference("messageCount/count");
    ImageView dp;
    Context context = this;
    List<messageCardModel> cards = new ArrayList<>();
    private String message;
    private String username;
    public long messageCount = 0;
    private RecyclerView recyclerView;
    private messageCardAdapter MessageCardAdapter;
    List<String> allUserNames=new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masala_chat);
       // dp.setImageDrawable(Drawable.createFromPath("@drawable/pp2.jpg"));

        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.message_card,recyclerView,false);


//        //SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
        SharedPreferences sharedPref = getSharedPreferences("MyData",MODE_PRIVATE);
        String name = sharedPref.getString("name", null);

        username = name;

        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();

        imageCard= findViewById(R.id.imagecard);
        textlayout=findViewById(R.id.textlayout);
        userinput = findViewById(R.id.userinput);
        sendbutton = findViewById(R.id.sendbutton);
        recyclerView = findViewById(R.id.recycler_view);
        dp = view.findViewById(R.id.userimage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MessageCardAdapter = new messageCardAdapter(cards, context);
        allUserNames.add("null");


        getCards();
     //   imageCard.setBackground(Drawable.createFromPath("@drawable/pp2"));



        messageCountRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                messageCountRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        DataSnapshot snapshot = task.getResult();
                        messageCount= (long) snapshot.getValue();
                        Toast.makeText(MasalaChat.this, String.valueOf(messageCount), Toast.LENGTH_SHORT).show();
                    }
                });

                databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        long NewmessageCount = task.getResult().getChildrenCount();
                        if (NewmessageCount > messageCount) {
                            int difference = (int) ((NewmessageCount - 1) - messageCount);
                        }

                        for (long i = messageCount; i <= NewmessageCount; i++) {
                            String messageChild = "message" + i;
                            String message = task.getResult().child(messageChild).child("message").getValue(String.class);
                            String sender = task.getResult().child(messageChild).child("sender").getValue(String.class);
                            String time=task.getResult().child(messageChild).child("time").getValue().toString();
                            if(sender.equals(username)){
                                continue;
                            }
                            else {
                                cards.add(new messageCardModel(sender, message,time));
                                MessageCardAdapter.notifyDataSetChanged();
                                int position = MessageCardAdapter.getItemCount() - 1;
                                recyclerView.smoothScrollToPosition(position);
//                                        recyclerView.scrollToPosition(position);
//                                        messageCountRef.setValue(NewmessageCount);
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

                int position=MessageCardAdapter.getItemCount()-1;
                recyclerView.scrollToPosition(position);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                messageCount++;
                DatabaseReference messageRef = database.getReference("messages/message" + messageCount + "/message");
                DatabaseReference usernameRef = database.getReference("messages/message" + messageCount + "/sender");
                DatabaseReference timestampRef = database.getReference("messages/message" + messageCount + "/time");

                message = userinput.getText().toString();
                String sender = username;
                messageRef.setValue(message);
                usernameRef.setValue(sender);
                sendername=sender;
                String time=new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());
                timestampRef.setValue(time);
                messageCountRef.setValue(messageCount);
                Toast.makeText(MasalaChat.this,  String.valueOf(messageCount), Toast.LENGTH_SHORT).show();
                userinput.setText("");

                cards.add(new messageCardModel(sender, message,time));
                MessageCardAdapter.notifyItemInserted(cards.size() - 1);

                int position=MessageCardAdapter.getItemCount()-1;
                recyclerView.scrollToPosition(position);

            }

        });


    }



    public void getCards(){
        databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                messageCount = task.getResult().getChildrenCount();
                DataSnapshot snapshot = task.getResult();
//                messageCountRef.setValue(messageCount);
                Toast.makeText(MasalaChat.this, String.valueOf(messageCount), Toast.LENGTH_SHORT).show();
                for (int i = 1; i <= messageCount; i++) {
                    String messageChild = "message" + i;
                    if(task.getResult().child(messageChild).child("message").getValue(String.class)=="") {
                        continue;

                    }else{
                        String message = task.getResult().child(messageChild).child("message").getValue(String.class);
                        String sender = task.getResult().child(messageChild).child("sender").getValue(String.class);
                        String time=task.getResult().child(messageChild).child("time").getValue().toString();
                      //  dp.setImageDrawable(Drawable.createFromPath("res/drawable/pp2.jpg"));
                        allUserNames.add(i,sender);
                        Log.d("Usernamane:",sender);
                        cards.add(new messageCardModel(sender, message,time));
                        MessageCardAdapter.notifyDataSetChanged();
                        int position=MessageCardAdapter.getItemCount()-1;
                        recyclerView.scrollToPosition(position);
                    }
                }
            }
        });
        MessageCardAdapter.getUsername(allUserNames);
        MessageCardAdapter=new messageCardAdapter(cards, context);
        recyclerView.setAdapter(MessageCardAdapter);



    }


    public void addCards(String name,String message){

    }
}