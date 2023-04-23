package com.example.travelbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerBusAdapter extends RecyclerView.Adapter<RecyclerBusAdapter.ViewHolder> {
    private ArrayList<BusData> arrayList;
    public  RecyclerBusAdapter(ArrayList<BusData> arrayList){
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(R.layout.view_bus_layout, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BusData busData = arrayList.get(position);
        holder.name.setText(busData.getName());
        holder.email.setText(busData.getEmail());
        holder.from.setText(busData.getFrom());
        holder.to.setText(busData.getTo());
        holder.user.setText(busData.getUser());
        holder.date.setText(busData.getDate());
        holder.time.setText(busData.getTime());
        holder.people.setText(busData.getPeople());
        holder.classs.setText(busData.getClasss());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView email;
        TextView from;
        TextView to;
        TextView user;
        TextView date;
        TextView time;
        TextView people;
        TextView classs;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.fname);
            email=itemView.findViewById(R.id.email);
            from=itemView.findViewById(R.id.from);
            to=itemView.findViewById(R.id.to);
            user=itemView.findViewById(R.id.user);
            date=itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.time);
            people=itemView.findViewById(R.id.people);
            classs=itemView.findViewById(R.id.classs);

        }
    }
}
