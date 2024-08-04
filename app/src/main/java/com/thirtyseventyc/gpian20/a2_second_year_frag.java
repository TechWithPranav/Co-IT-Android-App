package com.thirtyseventyc.gpian20;

import android.os.Bundle;


import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.example.gpian20.R;

public class a2_second_year_frag extends Fragment {

    CardView data_structures,dtmp,elect_engineering,edp1,cg,pp_1,rdms,c_prog_advance,computer_network,maths_3,os;
    public a2_second_year_frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.a2_fragment_a2_second_year_frag, container, false);


        data_structures = (CardView) view.findViewById(R.id.data_structures);
        dtmp = (CardView) view.findViewById(R.id.dtmp);
        elect_engineering = (CardView) view.findViewById(R.id.elect_engineering);
        edp1 = (CardView) view.findViewById(R.id.edp1);
        cg = (CardView) view.findViewById(R.id.cg);
        pp_1 = (CardView) view.findViewById(R.id.pp_1);
        rdms = (CardView) view.findViewById(R.id.rdms);
        c_prog_advance = (CardView) view.findViewById(R.id.c_prog);
        computer_network = (CardView) view.findViewById(R.id.computer_network);
        maths_3 = (CardView) view.findViewById(R.id.maths_3);
        os = (CardView) view.findViewById(R.id.os);


        data_structures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("data_structures", "data_structures");
                startActivity(intent);
            }
        });

        dtmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("dtmp", "dtmp");
                startActivity(intent);
            }
        });

        elect_engineering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("elect_engineering", "elect_engineering");
                startActivity(intent);
            }
        });

        edp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("edp1", "edp1");
                startActivity(intent);
            }
        });

        cg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("cg", "cg");
                startActivity(intent);
            }
        });

        pp_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("pp_1", "pp_1");
                startActivity(intent);
            }
        });

        rdms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("rdms", "rdms");
                startActivity(intent);
            }
        });

        c_prog_advance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("c_prog_advance", "c_prog_advance");
                startActivity(intent);
            }
        });

        computer_network.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("computer_network", "computer_network");
                startActivity(intent);
            }
        });

        maths_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("maths_3", "maths_3");
                startActivity(intent);
            }
        });

        os.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), a3_main_notes_all.class);
                intent.putExtra("os", "os");
                startActivity(intent);
            }
        });





        return view;
    }
}
