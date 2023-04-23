package com.example.travelbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class offer_frag extends Fragment {
//    Button b1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_offer_frag, container, false);
        Button button = (Button) view.findViewById(R.id.book);
        Button button2 = (Button) view.findViewById(R.id.book2);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent j = new Intent(getActivity(), Main_Payment1.class);
                startActivity(j);
            }
        });

        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent j = new Intent(getActivity(), Main_Payment1.class);
                startActivity(j);
            }
        });
        return view;
//        return inflater.inflate(R.layout.fragment_offer_frag, container, false);
    }
}