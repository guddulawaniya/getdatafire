package com.example.getfirebasedata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter  extends  RecyclerView.Adapter<adapter.viewholder>{

    ArrayList<getdatamodel> list;
    Context context;

    public adapter(ArrayList<getdatamodel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_model,parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        getdatamodel getdat = list.get(position);
        holder.name.setText(getdat.getSname());
        holder.email.setText(getdat.getSemail());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView name ,email;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.sname);
            email = itemView.findViewById(R.id.semail);
        }
    }
}
