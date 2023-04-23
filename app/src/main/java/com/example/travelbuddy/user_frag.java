package com.example.travelbuddy;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class user_frag extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_frag, container, false);
    }

    public void logout(View view)
    {
        Intent myIntent = new Intent(getActivity(), MainActivity.class);   //NEW WAY
        startActivity(myIntent);
    }

}