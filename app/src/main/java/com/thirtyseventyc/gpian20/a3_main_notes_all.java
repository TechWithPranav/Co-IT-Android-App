package com.thirtyseventyc.gpian20;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gpian20.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;



import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class a3_main_notes_all extends AppCompatActivity {
    FloatingActionButton new_notes_java_1;

    RecyclerView  mrecyclerView;
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    FirestoreRecyclerAdapter<firebasemodel,NoteViewHolder> noteAdapter;

    String data="cloud_computing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a3_activity_notes_main_all);



        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("THIRD YEAR");
        }


        Intent intent = getIntent();

        // for third year

        String value1 = intent.getStringExtra("acn"); // retrieve data using key
        String value2 = intent.getStringExtra("java_1"); // retrieve data using key
        String value3 = intent.getStringExtra("cloud_computing"); // retrieve data using key
        String value4 = intent.getStringExtra("pp_2"); // retrieve data using key
        String value5 = intent.getStringExtra("seminar"); // retrieve data using key
        String value6 = intent.getStringExtra("javaScript"); // retrieve data using key
        String value7 = intent.getStringExtra("SoftwareEngineering"); // retrieve data using key
        String value8 = intent.getStringExtra("android12"); // retrieve data using key
        String value9 = intent.getStringExtra("cs_security"); // retrieve data using key
        String value10 = intent.getStringExtra("dmi"); // retrieve data using key
        String value11= intent.getStringExtra("jp_2"); // retrieve data using key
        String value12 = intent.getStringExtra("project"); // retrieve data using key
        String value13 = intent.getStringExtra("python"); // retrieve data using key



        // for first year
        String valu1 = intent.getStringExtra("maths_1");
        String valu2 = intent.getStringExtra("ce");
        String valu3 = intent.getStringExtra("cms_1");
        String valu4 = intent.getStringExtra("physics");
        String valu5 = intent.getStringExtra("fict");
        String valu6 = intent.getStringExtra("web_development");
        String valu7 = intent.getStringExtra("cms_2");
        String valu8 = intent.getStringExtra("electronics");
        String valu9 = intent.getStringExtra("linux");
        String valu10 = intent.getStringExtra("maths_2");

        String valueData = intent.getStringExtra("data");


        // for third year
        if (value1 != null && value1.equals("acn_notes")) {
            data = "acn_notes";
        }
        else if (value2 != null && value2.equals("notes")) {
            data = "notes";
        }
        else if (value3 != null && value3.equals("cloud_computing")) {
            data = "cloud_computing";
        }
        else if (value4 != null && value4.equals("pp_2")) {
            data = "pp_2";
        }
        else if (value5 != null && value5.equals("seminar")) {
            data = "seminar";
        }
        else if (value6 != null && value6.equals("javaScript")) {
            data = "javaScript";
        }
        else if (value7 != null && value7.equals("SoftwareEngineering")) {
            data = "SoftwareEngineering";
        }
        else if (value8 != null && value8.equals("android12")) {
            data = "android12";
        }
        else if (value9 != null && value9.equals("cs_security")) {
            data = "cs_security";
        }
        else if (value10 != null && value10.equals("dmi")) {
            data = "dmi";
        }
        else if (value11 != null && value11.equals("jp_2")) {
            data = "jp_2";
        }
        else if (value12 != null && value12.equals("project")) {
            data = "project";
        }
        else if (value13 != null && value13.equals("python")) {
            data = "python";
        }

        //for third year
        else if (valu1 != null && valu1.equals("maths_1")) {
            data = "maths_1";
        }
        else if (valu2 != null && valu2.equals("ce")) {
            data = "ce";
        }

        else if (valu3 != null && valu3.equals("cms_1")) {
            data = "cms_1";
        }

        else if (valu4 != null && valu4.equals("physics")) {
            data = "physics";
        }

        else if (valu5 != null && valu5.equals("fict")) {
            data = "fict";
        }

        else if (valu6 != null && valu6.equals("web_development")) {
            data = "web_development";
        }

        else if (valu7 != null && valu7.equals("cms_2")) {
            data = "cms_2";
        }

        else if (valu8 != null && valu8.equals("electronics")) {
            data = "electronics";
        }

        else if (valu9 != null && valu9.equals("linux")) {
            data = "linux";
        }

        else if (valu10 != null && valu10.equals("maths_2")) {
            data = "maths_2";
        } else {

                    data = valueData;

        }

        new_notes_java_1 = findViewById(R.id.new_notes_java_1);

        new_notes_java_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(a3_main_notes_all.this, a5_notes_insert.class);
                intent.putExtra("key3", data);
                startActivity(intent);
            }
        });

        // to load the data from firebase
        Query query = firebaseFirestore.collection(data).orderBy("Title",Query.Direction.ASCENDING);


        FirestoreRecyclerOptions<firebasemodel> allusernotes = new FirestoreRecyclerOptions.Builder<firebasemodel>().setQuery(query,firebasemodel.class).build();
        noteAdapter = new FirestoreRecyclerAdapter<firebasemodel, NoteViewHolder>(allusernotes) {
            @Override
            protected void onBindViewHolder(@NonNull NoteViewHolder holder, int i, @NonNull firebasemodel firebasemodel) {


                // for menu or update,delete and view

                ImageView popupbutton = holder.itemView.findViewById(R.id.menupopButton);

                //--------------------------------------------------------------------------------------------continued downside



                int colourcode = getRandomColor();
                holder.mnote.setBackgroundColor(holder.itemView.getResources().getColor(colourcode,null));

                if (holder.notetitle != null) {
                    holder.notetitle.setText(firebasemodel.getTitle());
                }
//                holder.notetitle.setText(firebasemodel.getTitle());
                holder.noteSubTitle.setText(firebasemodel.getSubTitle());

                String docId = noteAdapter.getSnapshots().getSnapshot(i).getId();


                // for moving noteview to detail note activity for brief discription
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // here we are opening note detail activity

                        Intent intent = new Intent(view.getContext(), a4_notes_detail.class);
                        intent.putExtra("title",firebasemodel.getTitle());
                        intent.putExtra("subtitle",firebasemodel.getSubTitle());
                        intent.putExtra("notes_main_data",firebasemodel.getNotes());
                        intent.putExtra("notes_url",firebasemodel.getUrl());
                        intent.putExtra("noteId",docId);

                        view.getContext().startActivity(intent);

                    }
                });


                // --------------------continued imageview menu---------------------------------------------------


//                popupbutton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        PopupMenu popupMenu = new PopupMenu(view.getContext(),view);
//                        popupMenu.setGravity(Gravity.END);
//
//                        // for open
//                        popupMenu.getMenu().add("Open").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//                            @Override
//                            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
//                                Intent intent = new Intent(view.getContext(), a4_notes_detail.class);
//                                intent.putExtra("title",firebasemodel.getTitle());
//                                intent.putExtra("subtitle",firebasemodel.getSubTitle());
//                                intent.putExtra("notes_main_data",firebasemodel.getNotes());
//                                intent.putExtra("notes_url",firebasemodel.getUrl());
//                                intent.putExtra("noteId",docId);
//
//                                view.getContext().startActivity(intent);
//
//                                return false;
//                            }
//                        });
//
//                        // for update
//                        popupMenu.getMenu().add("Update").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//                            @Override
//                            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
//
//
//                                Intent intent = new Intent(view.getContext(), a6_notes_update.class);
//                                intent.putExtra("title",firebasemodel.getTitle());
//                                intent.putExtra("subtitle",firebasemodel.getSubTitle());
//                                intent.putExtra("notes_main_data",firebasemodel.getNotes());
//                                intent.putExtra("notes_url",firebasemodel.getUrl());
//                                intent.putExtra("noteId",docId);
//                                intent.putExtra("key5", data);
//                                view.getContext().startActivity(intent);
//                                return false;
//                            }
//                        });
//
//                        // for delete
//
//                        popupMenu.getMenu().add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//                            @Override
//                            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
//
//
//
//                                // to get the id of particular note
//                                DocumentReference documentReference = firebaseFirestore.collection(data).document(docId);
//
//                                documentReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void unused) {
//                                        Toast.makeText(a3_main_notes_all.this, "This note is deleted", Toast.LENGTH_SHORT).show();
//                                    }
//                                }).addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        Toast.makeText(a3_main_notes_all.this, "This note is not deleted", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//
//
//
//                                return false;
//                            }
//                        });
//                        popupMenu.show();
//
//                    }
//                });









                // new one for menubuton disable and enable


                String email = "pranavkolhe@gmail.com"; // replace with actual email
                String email2 = "fake@gmail.com"; // replace with actual email

                if (email.equals(email)) {
                    popupbutton.setEnabled(true); // enable popup button
                    popupbutton.setVisibility(View.VISIBLE); // make button visible
                    popupbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            PopupMenu popupMenu = new PopupMenu(view.getContext(),view);
                            popupMenu.setGravity(Gravity.END);

                            // for open
                            popupMenu.getMenu().add("Open").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                                    Intent intent = new Intent(view.getContext(),a4_notes_detail.class);
                                    intent.putExtra("title",firebasemodel.getTitle());
                                    intent.putExtra("subtitle",firebasemodel.getSubTitle());
                                    intent.putExtra("notes_main_data",firebasemodel.getNotes());
                                    intent.putExtra("notes_url",firebasemodel.getUrl());
                                    intent.putExtra("noteId",docId);

                                    view.getContext().startActivity(intent);

                                    return false;
                                }
                            });

                            // for update
                            popupMenu.getMenu().add("Update").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                                    Intent intent = new Intent(view.getContext(), a6_notes_update.class);
                                    intent.putExtra("title",firebasemodel.getTitle());
                                    intent.putExtra("subtitle",firebasemodel.getSubTitle());
                                    intent.putExtra("notes_main_data",firebasemodel.getNotes());
                                    intent.putExtra("notes_url",firebasemodel.getUrl());
                                    intent.putExtra("noteId",docId);

                                    intent.putExtra("key5", data);
                                    view.getContext().startActivity(intent);
                                    return false;
                                }
                            });

                            // for delete
                            popupMenu.getMenu().add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(@NonNull MenuItem menuItem) {

                                    // to get the id of particular note
                                    DocumentReference documentReference = firebaseFirestore.collection(data).document(docId);

                                    documentReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(a3_main_notes_all.this, "This note is deleted", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(a3_main_notes_all.this, "This note is not deleted", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    return false;
                                }
                            });
                            popupMenu.show();

                        }
                    });
                } else {
                    popupbutton.setEnabled(false); // disable popup button
                    popupbutton.setVisibility(View.GONE); // hide button from layout
                }

            }





            @NonNull
            @Override
            public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                // here if not work change layout file and try
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_card_layout_for_recycler,parent,false);
                return new NoteViewHolder(view);
            }
        };

        mrecyclerView = findViewById(R.id.java_notes_recycler);
        mrecyclerView.setHasFixedSize(true);
        staggeredGridLayoutManager= new StaggeredGridLayoutManager(2,staggeredGridLayoutManager.VERTICAL);
        mrecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mrecyclerView.setAdapter(noteAdapter);


    }
    public class NoteViewHolder extends RecyclerView.ViewHolder{

        private TextView notetitle;
        private TextView noteSubTitle;
        LinearLayout mnote;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            notetitle = itemView.findViewById(R.id.note_title);
            noteSubTitle = itemView.findViewById(R.id.subTitle);
            mnote = itemView.findViewById(R.id.note);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        noteAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();


        if(noteAdapter!=null){
            noteAdapter.startListening();
        }
    }

    private  int getRandomColor(){
        List<Integer> colorcode= new ArrayList<>();

        colorcode.add(R.color.color1);
        colorcode.add(R.color.color2);
        colorcode.add(R.color.color3);
        colorcode.add(R.color.color4);
        colorcode.add(R.color.color5);
        colorcode.add(R.color.color6);
        colorcode.add(R.color.color7);
        colorcode.add(R.color.color8);
        colorcode.add(R.color.color9);
        colorcode.add(R.color.color10);
        colorcode.add(R.color.color11);
        colorcode.add(R.color.color12);
        colorcode.add(R.color.color13);
        colorcode.add(R.color.color14);
        colorcode.add(R.color.color15);
        colorcode.add(R.color.color16);
        colorcode.add(R.color.color17);
        colorcode.add(R.color.color18);
        colorcode.add(R.color.color19);
        colorcode.add(R.color.color20);

        Random random = new Random();
        int number = random.nextInt(colorcode.size());
        return colorcode.get(number);
    }

    // to handle back pressed event of action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This will take the user back to the previous activity
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

