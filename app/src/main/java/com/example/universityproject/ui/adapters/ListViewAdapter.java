package com.example.universityproject.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.universityproject.R;
import com.example.universityproject.logic.models.RadioItem;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<RadioItem> {
    private final LayoutInflater inflater;
    private final int layout;
    private final List<RadioItem> states;

    public ListViewAdapter(Context context, int resource, List<RadioItem> states) {
        super(context, resource, states);
        this.inflater = LayoutInflater.from(context);
        this.layout = resource;
        this.states = states;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(this.layout, parent, false);
        ImageView radioImage = view.findViewById(R.id.radioImageView);
        TextView textView = view.findViewById(R.id.nameOfRadioStation);

        RadioItem state = states.get(position);

        radioImage.setImageResource(state.getPictureResource());
        textView.setText(state.getStationName());

        return view;
    }
}
