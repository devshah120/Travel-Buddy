package com.example.travelbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerBookingAdapter extends RecyclerView.Adapter<RecyclerBookingAdapter.ViewHolder>{

    private ArrayList<BookingData> arrayList;
    public RecyclerBookingAdapter(ArrayList<BookingData> arrayList){
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(R.layout.view_booking_layout, null);
        ViewHolder viewHolder = new ViewHolder(inflate);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookingData bookingData = arrayList.get(position);
        holder.fname.setText(bookingData.getFname());
        holder.lname.setText(bookingData.getLname());
        holder.email.setText(bookingData.getEmail());
        holder.contactno.setText(bookingData.getContactno());
        holder.user.setText(bookingData.getUser());
        holder.checkIn.setText(bookingData.getCheckIn());
        holder.checkOut.setText(bookingData.getCheckOut());
        holder.place.setText(bookingData.getPlace());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fname;
        TextView lname;
        TextView email;
        TextView contactno;
        TextView user;
        TextView checkIn;

        TextView checkOut;

        TextView place;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);
            email = itemView.findViewById(R.id.email);
            contactno = itemView.findViewById(R.id.number);
            user = itemView.findViewById(R.id.user);
            checkIn = itemView.findViewById(R.id.in_date);
            checkOut=itemView.findViewById(R.id.out_date);
            place=itemView.findViewById(R.id.place);
        }
    }
}
