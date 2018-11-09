package com.example.tom.mineclicker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class minerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    String[] items;
    public minerAdapter(Context context, String[] items){
        this.context = context;
        this.items = items;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.minercostumrow, parent,false);
        item item = new item(row);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return items.length;

    }
    public class item extends RecyclerView.ViewHolder{
        TextView textView;
        public item(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.item);
        }
    }


}

