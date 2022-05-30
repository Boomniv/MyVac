package com.example.myvac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OptionsAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<vacOptions> list;
    private int layout;

    public OptionsAdapter(@NonNull Context context, int layout, @NonNull ArrayList<vacOptions> list)
    {
        super(context,layout,list);

        this.context = context;
        this.layout = layout;
        this.list = list;
    }
    @Override
    public View getView(int position , @Nullable View convertView, @Nullable ViewGroup parent)
    {
        LayoutInflater layoutInflater = ((AppCompatActivity)context).getLayoutInflater();
        View view = layoutInflater.inflate(layout,parent,false);
        vacOptions options = this.list.get(position);

        TextView tvOptions = view.findViewById(R.id.tvOptions);
        ImageView imageHome = view.findViewById(R.id.imageHome);

        tvOptions.setText(options.getName());
        imageHome.setImageResource(options.getImage());

        return view;
    }
}
