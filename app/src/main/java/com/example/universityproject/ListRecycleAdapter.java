package com.example.universityproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

    @NonNull
    @Override
    public ListRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListRecycleAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
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
