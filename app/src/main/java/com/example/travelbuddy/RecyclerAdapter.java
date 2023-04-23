package com.example.travelbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<Data> arrayList;
    public RecyclerAdapter(ArrayList<Data> arrayList){
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(R.layout.user_data_layout, null);
        ViewHolder viewHolder =  new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data data = arrayList.get(position);
        holder.fname.setText(data.getFname());
        holder.lname.setText(data.getLname());
        holder.contactno.setText(data.getContactno());
        holder.email.setText(data.getEmail());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fname;
        TextView lname;
        TextView contactno;
        TextView email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);
            contactno = itemView.findViewById(R.id.number);
            email = itemView.findViewById(R.id.email);
        }
    }
}
