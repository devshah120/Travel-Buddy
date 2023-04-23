package com.example.travelbuddy;

import static android.service.controls.ControlsProviderService.TAG;
import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemChangeListener;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.travelbuddy.UtilsService.SharedPreferenceClass;
import com.example.travelbuddy.UtilsService.UtilService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Home_fragment_User extends Fragment implements RecyclerViewClickListerner {

    com.example.travelbuddy.databinding.ActivityMain3Binding binding;
    public loading_1 load;
    ImageSlider imageSlider;
    FloatingActionButton fabbutton;

    RecyclerView recyclerView;
    ArrayList<Model> arrayList; //ch
    ItemAdapter itemAdapter; //ch

    UtilService utilService;
    SharedPreferenceClass sharedPreferenceClass;
    TextView bali;

    ImageView flight, train, bus;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_home_fragment, container, false);
        load = new loading_1(getActivity());

//        fabbutton.setImageResource(R.drawable.bot);

        fabbutton =(FloatingActionButton) view.findViewById(R.id.chatbot);
//        fabbutton.setBackgroundColor(Color.parseColor("#FFFFFF"));
        fabbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Welcome to the Bot",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Chatbot.class);
                startActivity(intent);
            }
        });

        flight = (ImageView) view.findViewById(R.id.imageView2);
        flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "HELLO", LENGTH_SHORT).show();
                Intent j = new Intent(getActivity(), flight.class);
                startActivity(j);
            }
        });

        train = (ImageView) view.findViewById(R.id.imageView22);
        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getActivity(), train.class);
                startActivity(j);
            }
        });

        bus = (ImageView) view.findViewById(R.id.imageView23);
        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getActivity(), bus_bookig.class);
                startActivity(j);
            }
        });

        bali = (TextView) view.findViewById(R.id.textView);
        bali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getActivity(), MainActivity4.class);
                startActivity(j);
            }
        });
        imageSlider = view.findViewById(R.id.image_slider);       //SLIDER
//        ImageView image = (ImageView) view.findViewById(R.id.imageView2); //flight
//        ImageView image2 = (ImageView) view.findViewById(R.id.imageView17); //hotel
//        image.setOnClickListener(this);

//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent j = new Intent(getActivity(), MainActivity4.class);
//                startActivity(j);
//            }
//        });
//
//        image2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent j = new Intent(getActivity(), admin_user_add_place.class);
//                startActivity(j);
//            }
//        });

        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new

                SlideModel(R.drawable.one, "Moraine Lake", null));
        imageList.add(new

                SlideModel(R.drawable.two, "Assam Hill", null));
        imageList.add(new

                SlideModel(R.drawable.threee, "Taj Mahal", null));
        imageList.add(new

                SlideModel("https://cdn.londonandpartners.com/-/media/images/london/visit/things-to-do/sightseeing/london-attractions/coca-cola-london-eye/the-london-eye-2-640x360.jpg?mw=640&hash=F7D574072DAD523443450DF57E3B91530064E4EE", "London Eye", null));

        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP);

        this.imageSlider.setItemChangeListener(new ItemChangeListener() {
            @Override
            public void onItemChanged(int i) {
                Log.d(TAG, "pos :" + i); // working }

            }
        });

        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                Toast.makeText(getContext(), imageList.get(i).getTitle().toString(), LENGTH_SHORT).show();
            }
        });


        // Recycle view inside fragment
        recyclerView = view.findViewById(R.id.horizontal_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new
        LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false)); //this line can do horizontal view
//        recyclerView.setAdapter(new ItemAdapter(initData(), this));

//        Loadingdsfsdf();
        getCards();
        return view;
    }

    public  void getCards() {
        arrayList = new ArrayList<>();
            String url = "https://travel-buddy-api-production-8b1b.up.railway.app/api/cards";

        //json
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET
                ,
                url, null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {

                try {
                    if (response.getBoolean("success")){
//                        Toast.makeText(getActivity(), response.toString(), LENGTH_SHORT).show();
                        JSONArray jsonArray = response.getJSONArray("cards");

                        for (int i = 0; i<jsonArray.length(); i ++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            Model model = new Model(
                                    jsonObject.getString("_id"),
                                    jsonObject.getString("title"),
                                    jsonObject.getString("description"),
                                    jsonObject.getString("place"),
                                    jsonObject.getString("price")
                            );
                            arrayList.add(model);

                        }

                        itemAdapter = new ItemAdapter(getActivity(), arrayList, Home_fragment_User.this);
                        recyclerView.setAdapter(itemAdapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
//                Toast.makeText(admin_user_add_place.this, "ERROR Responece", Toast.LENGTH_SHORT).show();

                if (error instanceof ServerError && response != null){
                    try {
                        String res = new String(response.data, HttpHeaderParser.parseCharset(response.headers,"utf-8"));
                        JSONObject obj = new JSONObject(res);
                        Toast.makeText(getActivity(),obj.getString("msg"),Toast.LENGTH_SHORT).show();
                    }
                    catch (JSONException | UnsupportedEncodingException je){
                        je.printStackTrace();
                    }
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap<>();
                headers.put("Content-Type","application/json");
                return headers;
            }
        };

        //set retry policy
        int socketTime = 3000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTime,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);

        //request add
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onItemClick(int position) {
        Log.d(TAG, "onNoteClick: clicked");
        Toast.makeText(getContext(), "Welcome", Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(getActivity(), MainActivity4.class);
        if (position == 0) {
//            Toast.makeText(getContext(), "Rome", LENGTH_SHORT).show();
           myIntent = new Intent(getActivity(), MainActivity4.class);   //NEW WAY
            startActivity(myIntent);
        } else if (position == 1) {
            Toast.makeText(getContext(), "London", LENGTH_SHORT).show();
            myIntent = new Intent(getActivity(), MainActivity4.class);   //NEW WAY
            startActivity(myIntent);
        } else {
            myIntent = new Intent(getActivity(), MainActivity4.class);   //NEW WAY
            startActivity(myIntent);
        }

    }
    public void Loadingdsfsdf(){
//         loading
        load.show();
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                load.cancel();
            }
        };
        handler.postDelayed(runnable, 2000);
    }

//    public void onClick(int position) {
//        Intent myIntent = new Intent(getActivity(), MainActivity4.class);   //NEW WAY
//        startActivity(myIntent);
//    }

//    @Override
//    public void onItemClick(int position) {
//        Toast.makeText(getActivity(), "Position "+ position, LENGTH_SHORT).show();
//    }

//    public void onNoteClick(int position) {
//        Toast.makeText(getActivity(), "Position "+ position, LENGTH_SHORT).show();
//    }
}
