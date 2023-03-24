package com.example.universityproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.universityproject.R;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<RadioItem> {
    private LayoutInflater inflater;
    private int layout;
    private List<RadioItem> states;

    public ListViewAdapter(Context context, int resource,List<RadioItem> states) {
        super(context, resource, states);
        this.inflater = LayoutInflater.from(context);
        this.layout = resource;
        this.states = states;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View view = inflater.inflate(this.layout,parent,false);
        ImageView radioImage = view.findViewById(R.id.RadioImageView);
        TextView textView = view.findViewById(R.id.NameOfRadioStation);

        RadioItem state = states.get(position);

        radioImage.setImageResource(state.getPictureResource());
        textView.setText(state.getStationName());

        return view;
    }
}
