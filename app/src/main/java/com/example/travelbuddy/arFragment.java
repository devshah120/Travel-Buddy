package com.example.travelbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class arFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ar, container, false);

        Button arbutton = (Button) view.findViewById(R.id.arbtn);

        arbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClassName("com.wheic.arapp", "com.wheic.arapp.MainActivity");

                if (intent != null){
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(), "Hello AR", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}