package com.example.checkers;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterListView extends BaseAdapter {

    public ArrayList<HashMap<String,String>> list;
    private Activity activity;

    public AdapterListView(Activity activity, ArrayList<HashMap<String,String>> list)
    {
        super();
        this.activity =activity;
        this.list =list;
    }
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        // get object in place i
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        LayoutInflater inflater = activity.getLayoutInflater();
        viewHolder = new ViewHolder();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.column_row, null);
            viewHolder.col1 = (TextView) convertView.findViewById(R.id.playerName);
            viewHolder.col2 = (TextView) convertView.findViewById(R.id.score);

            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        HashMap<String, String> map = list.get(i);

        //Design the score list
        viewHolder.col1.setText(map.get(ListScores.FIRST_COLUMN));

        viewHolder.col1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        //set the text view size
        if (i == 0) {

            viewHolder.col1.setTextSize(22);
            viewHolder.col1.setTextColor(Color.GREEN);

        }
        else {
            viewHolder.col1.setTextSize(20);
            viewHolder.col1.setTextColor(Color.BLUE);

        }

        //Design the score list
        viewHolder.col2.setText(map.get(ListScores.SECOND_COLUMN));

        viewHolder.col2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        //set the text view size
        if(i == 0) {
            viewHolder.col2.setTextSize(22);
            viewHolder.col2.setTextColor(Color.GREEN);

        }
        else {
            viewHolder.col2.setTextSize(20);
            viewHolder.col2.setTextColor(Color.BLUE);

        }

        return convertView;
    }

    private class ViewHolder
    {
        TextView col1; // text view column in list view
        TextView col2; // text view column in list view
    }


}
