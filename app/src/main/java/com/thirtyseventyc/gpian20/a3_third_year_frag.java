package com.thirtyseventyc.gpian20;


import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.gpian20.R;


public class a3_third_year_frag extends Fragment {

    CardView acn_pointer,java_1_pointer,cloud_computing,pp_2,seminar,javaScript,SoftwareEngineering,android12,cs_security,dmi,jp_2,project,python;

    public a3_third_year_frag() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.a3_fragment_a3_third_year_frag, container, false);

        // calling the action bar
//        ActionBar actionBar = (AppCompatActivity)getSupportActionBar();
//        if (actionBar != null) {
//            // showing the back button in action bar
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setTitle("THIRD YEAR");
//        }






        acn_pointer = (CardView) view.findViewById(R.id.acn_notes);
        java_1_pointer = (CardView) view.findViewById(R.id.java_1_notes);
        cloud_computing = (CardView) view.findViewById(R.id.cloud_computing);
        pp_2 = (CardView) view.findViewById(R.id.pp_2);
        seminar = (CardView) view.findViewById(R.id.seminar);
        javaScript = (CardView) view.findViewById(R.id.javaScript);
        SoftwareEngineering = (CardView) view.findViewById(R.id.SoftwareEngineering);
        android12 = (CardView) view.findViewById(R.id.android12);
        cs_security = (CardView) view.findViewById(R.id.cs_security);
        dmi = (CardView) view.findViewById(R.id.dmi);
        jp_2 = (CardView) view.findViewById(R.id.jp_2);
        project = (CardView) view.findViewById(R.id.project);
        python = (CardView) view.findViewById(R.id.python);

        acn_pointer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("acn", "acn_notes");
                startActivity(intent);
            }
        });

        java_1_pointer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("java_1", "notes");
                startActivity(intent);
            }
        });

        cloud_computing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("cloud_computing", "cloud_computing");
                startActivity(intent);
            }
        });

        pp_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("pp_2", "pp_2");
                startActivity(intent);
            }
        });

        seminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("seminar", "seminar");
                startActivity(intent);
            }
        });

        javaScript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("javaScript", "javaScript");
                startActivity(intent);
            }
        });

        SoftwareEngineering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("SoftwareEngineering", "SoftwareEngineering");
                startActivity(intent);
            }
        });

        android12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("android12", "android12");
                startActivity(intent);
            }
        });

        cs_security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("cs_security", "cs_security");
                startActivity(intent);
            }
        });

        dmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("dmi", "dmi");
                startActivity(intent);
            }
        });

        jp_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("jp_2", "jp_2");
                startActivity(intent);
            }
        });

        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("project", "project");
                startActivity(intent);
            }
        });

        python.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("python", "python");
                startActivity(intent);
            }
        });





return  view;




    }


    // to handle back pressed event of action bar
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == android.R.id.home) {
//            // This will take the user back to the previous activity
//            onBackPressed();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


}