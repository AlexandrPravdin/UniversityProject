package com.example.universityproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.universityproject.adapter.RadioItem;

import java.util.List;

public class ListRecycleAdapter extends RecyclerView.Adapter<ListRecycleAdapter.ViewHolder> {
    private final OnListClickListener onClickListener;
    private final LayoutInflater inflater;
    private final List<RadioItem> items;
    ListRecycleAdapter(Context context, List<RadioItem> items, OnListClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ListRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListRecycleAdapter.ViewHolder holder, int position) {
        RadioItem item = items.get(position);
        holder.picView.setImageResource(item.getPictureResource());
        holder.nameView.setText(item.getStationName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onItemClick(item, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    interface OnListClickListener {
        void onItemClick(RadioItem item, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView picView;
        final TextView nameView;

        ViewHolder(View view) {
            super(view);
            picView = view.findViewById(R.id.radioImageView);
            nameView = view.findViewById(R.id.nameOfRadioStation);
        }
    }
}






/*
package com.example.universityproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universityproject.adapter.RadioItem;

import java.util.ArrayList;

public class ListRecycleAdapter extends RecyclerView.Adapter<ListRecycleAdapter.MyViewHolder> {
    Context context;
    ArrayList<RadioItem> stations;

    interface OnItemClickListener{
        void onItemClick(RadioItem item, int position);
    }



    public ListRecycleAdapter(Context context, ArrayList<RadioItem> stations) {
        this.context = context;
        this.stations = stations;
    }

    @NonNull
    @Override
    public ListRecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_item,parent,false);

        return new ListRecycleAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListRecycleAdapter.MyViewHolder holder, int position) {
        RadioItem item = stations.get(position);
        holder.textView.setText(stations.get(position).getStationName());
        holder.imageView.setImageResource(stations.get(position).getPictureResource());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onItemClick(item,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stations.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.radioImageView);
            textView = itemView.findViewById(R.id.nameOfRadioStation);
        }
    }
}
*/
