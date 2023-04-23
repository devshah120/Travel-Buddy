package com.example.travelbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerTrainAdapter extends RecyclerView.Adapter<RecyclerTrainAdapter.ViewHolder> {
    private ArrayList<TrainData> arrayList;
    public  RecyclerTrainAdapter(ArrayList<TrainData> arrayList){
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(R.layout.view_train_layout, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TrainData trainData = arrayList.get(position);
        holder.name.setText(trainData.getName());
        holder.email.setText(trainData.getEmail());
        holder.from.setText(trainData.getFrom());
        holder.to.setText(trainData.getTo());
        holder.user.setText(trainData.getUser());
        holder.date.setText(trainData.getDate());
        holder.time.setText(trainData.getTime());
        holder.people.setText(trainData.getPeople());
        holder.classs.setText(trainData.getClasss());
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
