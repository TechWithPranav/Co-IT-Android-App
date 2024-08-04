package com.thirtyseventyc.gpian20;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gpian20.R;

public class fragNotes extends Fragment {

    CardView firstYearButton, secondYearButton, thirdYearButton;
    ConstraintLayout fragNotesMain;

    public fragNotes() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view2 = inflater.inflate(R.layout.fragment_frag_notes, container, false);






        firstYearButton = view2.findViewById(R.id.first_year);
        secondYearButton = view2.findViewById(R.id.second_year);
        thirdYearButton = view2.findViewById(R.id.third_year);
        fragNotesMain = view2.findViewById(R.id.frag_notes_main);
//
//        firstYearButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentManager fragmentManager = getParentFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//
//
//                // Replace the entire layout container with a new instance of A1FirstYearFrag
//                fragmentTransaction.replace(R.id.frag_notes_main, new a1_first_year_frag());
//                fragmentTransaction.addToBackStack(null);
//
//                fragmentTransaction.commit();
//            }
//        });




        firstYearButton.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {


                firstYearButton.setVisibility(View.GONE);
                secondYearButton.setVisibility(View.GONE);
                thirdYearButton.setVisibility(View.GONE);
            //    FrameLayout fl = (FrameLayout) view.findViewById();
             //   fl.removeAllViews();

//                FragmentManager fragmentManager = getParentFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                  Fragment nextFragment = new a1_first_year_frag();
//                fragmentTransaction.replace(R.id.frag_notes_main, nextFragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frag_notes_main, nextFragment, "NewFragmentTag");
                ft.commit();
                ft.addToBackStack(null);

                // getSupportFragmentManager().beginTransaction().replace(R.id.frag_notes_main, nextFragment).commit();

            }


        });








        secondYearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firstYearButton.setVisibility(View.GONE);
                secondYearButton.setVisibility(View.GONE);
                thirdYearButton.setVisibility(View.GONE);

                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();



                // Replace the entire layout container with a new instance of A2SecondYearFrag
                fragmentTransaction.replace(R.id.frag_notes_main, new a2_second_year_frag());
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();
            }
        });

        thirdYearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstYearButton.setVisibility(View.GONE);
                secondYearButton.setVisibility(View.GONE);
                thirdYearButton.setVisibility(View.GONE);


                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();



                // Replace the entire layout container with a new instance of A2SecondYearFrag
                fragmentTransaction.replace(R.id.frag_notes_main, new a3_third_year_frag());
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();
            }
        });


        return view2;
    }
    public void hideButton() {
        firstYearButton.setVisibility(View.GONE);
    }


}
