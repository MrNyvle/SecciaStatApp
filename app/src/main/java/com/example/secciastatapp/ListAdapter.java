package com.example.secciastatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Stat> {

    public ListAdapter(Context context, ArrayList<Stat> statArrayList) {

        super(context, R.layout.list_item, statArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Stat stat = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        }

        TextView Name = convertView.findViewById(R.id.textViewName);
        TextView Likes = convertView.findViewById(R.id.textViewLikes);
        TextView Orders = convertView.findViewById(R.id.textViewOrders);

        assert stat != null;
        Name.setText(stat.Name);
        Likes.setText(String.valueOf(stat.Likes));
        Orders.setText(String.valueOf(stat.Orders));

        return convertView;
    }
}
