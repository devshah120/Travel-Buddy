package com.example.travelbuddy;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity4 extends AppCompatActivity implements ItemAdapter.OnNoteListener {

    ImageSlider imageSlider;
    RecyclerView recyclerView;
    List<Model> itemList;
    TextView title,desc,price,place;
    Model model = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        final Object obj = getIntent().getSerializableExtra("title");

        if (obj instanceof Model){
            model = (Model) obj;
        }
        title = findViewById(R.id.title);
        desc = findViewById(R.id.description);
        place = findViewById(R.id.place);
        price = findViewById(R.id.mrp);

        if (model!=null){
            // Glide.with(getApplicationContext());
            title.setText(model.getTitle());
            desc.setText(model.getDescription());
            place.setText(model.getPlace());
            price.setText(model.getPrice());

        }


//         // Recycle view inside fragment // mainactiiy 4
//        recyclerView = findViewById(R.id.horizontal_rcv);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)); //this line can do horizontal view
//        recyclerView.setAdapter(new ItemAdapter222(initData()));
//        return recyclerView;


    }
//    private List<DataModel> initData() {
//        itemList = new ArrayList<>();
//        itemList.add(new DataModel(R.drawable.du1,""));
//        itemList.add(new DataModel(R.drawable.du3,""));
//        itemList.add(new DataModel(R.drawable.du4,""));
//        itemList.add(new DataModel(R.drawable.dubai,""));
//        return itemList;
//    }

    @Override
    public void onNoteClick(int position) {

    }

    public void booking1(View view)
    {
//        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Main_Payment1.class);
        startActivity(intent);
    }
}