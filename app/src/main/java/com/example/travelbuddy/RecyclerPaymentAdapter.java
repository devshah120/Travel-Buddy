package com.example.travelbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerPaymentAdapter extends RecyclerView.Adapter<RecyclerPaymentAdapter.ViewHolder> {
    private ArrayList<PaymentData> arrayList;
    public RecyclerPaymentAdapter(ArrayList<PaymentData> arrayList){
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(R.layout.payment_layout, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PaymentData paymentData = arrayList.get(position);
        holder.cardname.setText(paymentData.getCardname());
        holder.cardnumber.setText(paymentData.getCardnumber());
        holder.expdate.setText(paymentData.getExpdate());
        holder.cvv.setText(paymentData.getCvv());
        holder.user.setText(paymentData.getUser());
        holder.createdAt.setText(paymentData.getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cardname;
        TextView cardnumber;
        TextView expdate;
        TextView cvv;
        TextView user;
        TextView createdAt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardname= itemView.findViewById(R.id.cardname);
            cardnumber = itemView.findViewById(R.id.cardnumber);
            expdate = itemView.findViewById(R.id.expdate);
            cvv = itemView.findViewById(R.id.cvv);
            user = itemView.findViewById(R.id.user);
            createdAt = itemView.findViewById(R.id.date);
        }
    }
}
