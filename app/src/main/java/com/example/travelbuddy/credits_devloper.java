package com.example.travelbuddy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class credits_devloper extends Fragment {
    ImageView s1,d1,t1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_credits_devloper, container, false);

        s1 =(ImageView) view.findViewById(R.id.imageView34);
        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.linkedin.com/in/shivam-brahmkshatriya-81b0a9208");
            }
        });

        d1 =(ImageView) view.findViewById(R.id.imageView37);
        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.linkedin.com/in/dev-shah-84a0ba1bb");
            }
        });

        t1 =(ImageView) view.findViewById(R.id.imageView38);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.linkedin.com/in/tirth-dave-886baa229");
            }
        });


//        return inflater.inflate(R.layout.fragment_credits_devloper, container, false);
        return view;
    }
    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}