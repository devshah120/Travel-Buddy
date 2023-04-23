package com.example.travelbuddy;

import static android.service.controls.ControlsProviderService.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.ArrayList;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    ArrayList<Model> arrayList;
    Context context;
     final private RecyclerViewClickListerner clickListerner;

    public ItemAdapter(Context context, ArrayList<Model> arrayList, RecyclerViewClickListerner clickListerner) {
        this.arrayList  = arrayList;
        this.context = context;
        this.clickListerner = clickListerner;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_listitems,parent,false);

        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "HELLO CLICK", Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        final String id = arrayList.get(position).getId();
        final String title = arrayList.get(position).getTitle();
        final String description = arrayList.get(position).getDescription();
//        final String place = arrayList.get(position).getPlace();
        final String price = arrayList.get(position).getPrice();

        holder.titleTv.setText(title);
        //holder.descriptionTv.setText(decription);//only 1 is passing
//        holder.cardView.setBackgroundResource();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MainActivity4.class);
                intent.putExtra("title",arrayList.get(holder.getAbsoluteAdapterPosition()));
                //intent.putExtra("description",arrayList.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView itemImage;
        CardView cardView;
        TextView titleTv, descriptionTv, click;
        OnNoteListener onNoteListener; //new

        CardView accordian_title;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            titleTv = itemView.findViewById(R.id.title); //only 1passing

//            titleTv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    clickListerner.onItemClick(getAbsoluteAdapterPosition());
//                }
//            });

//            cardView = itemView.findViewById(R.id.cutout);
        }

        @Override
        public void onClick(View view) {
        onNoteListener.onNoteClick(getAbsoluteAdapterPosition());
//            Log.d(TAG, "onNoteClick: clicked");
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }

}
