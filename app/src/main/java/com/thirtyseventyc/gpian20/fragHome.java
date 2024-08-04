package com.thirtyseventyc.gpian20;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.gpian20.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragHome extends Fragment {

    // Variables used
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String ipo;
    int num = 0;
    int ipost = 2;
    LinearLayout layout;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ProgressBar mainPro;

    public fragHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragHome.
     */
    // TODO: Rename and change types and number of parameters
    public static fragHome newInstance(String param1, String param2) {
        fragHome fragment = new fragHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_home, container, false);
    }


    @Override
    public void onStart() {
        layout = getActivity().findViewById(R.id.container);
        mainPro = getView().findViewById(R.id.mainProgresss);
        super.onStart();
        getCurrentNumberOfPosts();
//        addNewPost();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy | EEEE", Locale.getDefault());
        String today = dateFormat.format(calendar.getTime());
        TextView Datee = getActivity().findViewById(R.id.dateText);
        Datee.setText(today);
    }
    String UrlMain;
    Boolean isWeb = true;
    private void addNewPost(int thisPOst) {
        View view = getLayoutInflater().inflate(R.layout.card, null);
        view.setVisibility(view.GONE);
        TextView title = view.findViewById(R.id.noticeTitle2);
        TextView desc = view.findViewById(R.id.noticeDesc3);
        CardView attachCard = view.findViewById(R.id.attachCard);
        DocumentReference docRef = db.collection("All Posts").document("Post" + (thisPOst-1));
        try {
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        ipo = document.getString("title");
                        ipo = document.getString("desc");

                        String titleSet = document.getString("title");
                        String descSet = document.getString("desc");
                        String isAttachment = document.getString("attachment");
                        String urll = document.getString("url");
                        UrlMain = urll;

                        //--------------------------------------------------------
                        if (isAttachment.equals("false"))
                        {
                            attachCard.setVisibility(View.GONE);
                            isWeb = false;
                        }
                        //--------------------------------------------------------
                        if (descSet == "")
                        {
                            title.setText(titleSet);
                            desc.setText("No Description");
                        }
                        else{
                            title.setText(titleSet);
                            desc.setText(descSet);
                        }

                        if (document.exists()) {
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        } else {
                            Log.d(TAG, "No such document");
                        }
                        view.setVisibility(view.VISIBLE);
                        mainPro.setVisibility(view.GONE);

                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });


            layout.addView(view);

        }
        catch(Exception e){
            Log.d(TAG, "Failed to create a new card : "+ e.getMessage());
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isWeb = false)
                {
                    Toast.makeText(getContext(), "No File Attached", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(), "Opening", Toast.LENGTH_SHORT).show();

                    DocumentReference docRef = db.collection("All Posts").document("Post" + (thisPOst-1));

                    try {
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            DocumentSnapshot document = task.getResult();
                            String sUrl = document.getString("url");
                            Intent toWeb = new Intent(getContext(), showFile.class);
                            toWeb.putExtra("url", sUrl);
                            startActivity(toWeb);
                        }
                    });
                    }catch (Exception a){
                        Toast.makeText(getContext(), "LAgle", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }




    private void getCurrentNumberOfPosts() {
        try {
            DocumentReference docRef = db.collection("NumberOfCurrentPost").document("whichPost?");
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        ipo = document.getString("Count");
//                            String temp1 = document.getString("Number");
//                            num = Integer.parseInt(temp1);
                        num = Integer.parseInt(ipo);
                        ipost = Integer.parseInt(ipo);
                        Log.d(TAG, "ipost = " + ipost);

                        for (int i = (ipost) ; i>=(ipost-5); i--)
                        {
                            if (i>=1)
                            {
                                addNewPost(i);
                            }
                        }

                        if (document.exists()) {
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });

        }
        catch(Exception e)
        {
            Log.d(TAG, "Failed to get the Number");
        }
    }


}