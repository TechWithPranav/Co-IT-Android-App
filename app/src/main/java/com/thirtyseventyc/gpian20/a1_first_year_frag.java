package com.thirtyseventyc.gpian20;

import android.os.Bundle;


import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.example.gpian20.R;

public class a1_first_year_frag extends Fragment {


    CardView maths_1,ce,cms_1,physics,fict,web_development,cms_2,electronics,linux,maths_2;

    public a1_first_year_frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.a1_fragment_a1_first_year_frag, container, false);


        maths_1 = (CardView) view.findViewById(R.id.year_first);
        ce = (CardView) view.findViewById(R.id.ce);
        cms_1 = (CardView) view.findViewById(R.id.cms_1);
        physics = (CardView) view.findViewById(R.id.physics_1);
        fict = (CardView) view.findViewById(R.id.fict);
        web_development = (CardView) view.findViewById(R.id.web_development);
        cms_2 = (CardView) view.findViewById(R.id.cms_2);
        electronics = (CardView) view.findViewById(R.id.electronics);
        linux = (CardView) view.findViewById(R.id.linux);
        maths_2 = (CardView) view.findViewById(R.id.maths_2);


        maths_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("maths_1", "maths_1");
                startActivity(intent);
            }
        });

        ce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("ce", "ce");
                startActivity(intent);
            }
        });

        cms_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("cms_1", "cms_1");
                startActivity(intent);
            }
        });

        physics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("physics", "physics");
                startActivity(intent);
            }
        });

        fict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("fict", "fict");
                startActivity(intent);
            }
        });

        web_development.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("web_development", "web_development");
                startActivity(intent);
            }
        });

        cms_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("cms_2", "cms_2");
                startActivity(intent);
            }
        });

        electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("electronics", "electronics");
                startActivity(intent);
            }
        });

        linux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("linux", "linux");
                startActivity(intent);
            }
        });

        maths_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("maths_2", "maths_2");
                startActivity(intent);
            }
        });



        return view;

    }

//    public void onDetach(LayoutInflater inflater, ViewGroup container) {
//        super.onDetach();
//        Toast.makeText(getContext(), "asasd", Toast.LENGTH_SHORT).show();
//        CardView firstYearButton, secondYearButton, thirdYearButton;
//        View view2 = inflater.inflate(R.layout.fragment_frag_notes, container, false);
//
//        firstYearButton = view2.findViewById(R.id.first_year);
//        secondYearButton = view2.findViewById(R.id.second_year);
//        thirdYearButton = view2.findViewById(R.id.third_year);
//        firstYearButton.setVisibility(View.VISIBLE);
//        secondYearButton.setVisibility(View.VISIBLE);
//        thirdYearButton.setVisibility(View.VISIBLE);
//    }
}
