package com.thirtyseventyc.gpian20;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.gpian20.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragChat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragChat extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public fragChat() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragChat.
     */
    // TODO: Rename and change types and number of parameters
    public static fragChat newInstance(String param1, String param2) {
        fragChat fragment = new fragChat();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    Context context = getActivity();

    private EditText userinput;
    private ImageButton sendbutton;
    private CardView imageCard;
    private LinearLayout textlayout;
    public static String sendername="";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference("messages");
    DatabaseReference messageCountRef = database.getReference("messageCount/count");

    List<messageCardModel> cards = new ArrayList<>();
    private String message;
    private String username = "samay";
    public long messageCount = 0;
    private RecyclerView recyclerView;
    private messageCardAdapter MessageCardAdapter;

    List<String> allUserNames=new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//       setContentView(R.layout.chatactivity_main);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        View view = getLayoutInflater().inflate(R.layout.fragment_frag_chat, recyclerView, false);
        View view1=getLayoutInflater().inflate(R.layout.message_card,null,false);
        imageCard = view1.findViewById(R.id.imagecard);
        textlayout = view1.findViewById(R.id.textlayout);
        userinput = view.findViewById(R.id.userinput);
        sendbutton = view.findViewById(R.id.sendbutton);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MessageCardAdapter = new messageCardAdapter(cards, context);
        allUserNames.add("null");


        getCards();


        messageCountRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                messageCountRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        DataSnapshot snapshot = task.getResult();
                        messageCount= (long) snapshot.getValue();
                        Toast.makeText(getContext(), String.valueOf(messageCount), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(),  String.valueOf(messageCount), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(), String.valueOf(messageCount), Toast.LENGTH_SHORT).show();
                for (int i = 1; i <= messageCount; i++) {
                    String messageChild = "message" + i;
                    if(task.getResult().child(messageChild).child("message").getValue(String.class)=="") {
                        continue;

                    }else{
                        String message = task.getResult().child(messageChild).child("message").getValue(String.class);
                        String sender = task.getResult().child(messageChild).child("sender").getValue(String.class);
                        String time=task.getResult().child(messageChild).child("time").getValue().toString();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_chat, container, false);
    }
}