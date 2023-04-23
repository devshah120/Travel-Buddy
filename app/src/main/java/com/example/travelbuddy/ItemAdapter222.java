package com.example.travelbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter222 extends RecyclerView.Adapter<ItemAdapter222.ViewHolder> {

    List<Model> itemList1;

    OnNoteListener onNoteListener;

    public ItemAdapter222(List<Model> itemList){

        this.itemList1=itemList;
//        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitems2,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        holder.itemImage.setImageResource(itemList1.get(position).getImage());
//        holder.itemtxt.setText(itemList1.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return itemList1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView itemImage;
//        TextView itemtxt;
        OnNoteListener onNoteListener; //new

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage=itemView.findViewById(R.id.dubai_acti);

//            this.onNoteListener = onNoteListener;
//            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        onNoteListener.onNoteClick(getAbsoluteAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }

}
