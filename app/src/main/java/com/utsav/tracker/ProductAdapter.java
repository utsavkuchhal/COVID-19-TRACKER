package com.utsav.tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Data> {

    private final Context context;
    private final ArrayList<Data> values;


    public ProductAdapter(Context context,ArrayList<Data> list)
    {
        super(context,R.layout.row_layout,list);
        this.context = context;
        this.values = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater  layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);

        TextView state = view.findViewById(R.id.state);
        TextView active = view.findViewById(R.id.active);
        TextView confirm = view.findViewById(R.id.confirm);
        TextView death = view.findViewById(R.id.deceased);
        TextView recovered = view.findViewById(R.id.recovered);

        state.setText(values.get(position).getState());
        active.setText(values.get(position).getActive());
        confirm.setText(values.get(position).getConfirm());
        death.setText(values.get(position).getDeath());
        recovered.setText(values.get(position).getRecovered());



        return view;
    }
}
