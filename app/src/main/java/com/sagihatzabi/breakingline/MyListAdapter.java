package com.sagihatzabi.breakingline;

/**
 * Created by Yoav on 10-Mar-17.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends ArrayAdapter<String> {

    Context context;
    int layoutResourceId;
    ArrayList<String> data = null;
    int color;

    public MyListAdapter(Context context, int layoutResourceId, ArrayList<String> data, int color) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        this.color = color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        StringHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new StringHolder();
            holder.txtTitle = (TextView)row.findViewById(R.id.text1);

            row.setTag(holder);
        }
        else
        {
            holder = (StringHolder)row.getTag();
        }

        holder.txtTitle.setText(data.get(position));
        row.setBackgroundColor(color);

        return row;
    }

    static class StringHolder
    {
        TextView txtTitle;
    }
}
