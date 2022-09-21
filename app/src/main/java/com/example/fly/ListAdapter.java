package com.example.fly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fly.ListModel.dataList;

import java.util.List;

public class ListAdapter extends ArrayAdapter<dataList> {
    public ListAdapter(@NonNull Context context, int resource, @NonNull List<dataList> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, @Nullable View converView, @NonNull ViewGroup parent){
        dataList dataList = getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(R.layout.list,parent,false);
        TextView toDate = view.findViewById(R.id.toDate);
        toDate.setText(dataList.getStarttime());
        TextView daDate = view.findViewById(R.id.daDate);
        daDate.setText(dataList.getEndtime());
        return view;
    }
}
