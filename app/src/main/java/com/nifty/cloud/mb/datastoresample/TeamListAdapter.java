package com.nifty.cloud.mb.datastoresample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;

/**
 * Created by fukai on 16/03/03.
 */
public class TeamListAdapter extends ArrayAdapter<Team> {

    public TeamListAdapter(Context context){
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = View.inflate(getContext(), R.layout.list, null);
        }

        Team item = getItem(position);

        TextView team_nameTextView = (TextView) convertView.findViewById(R.id.team_name);
        TextView locationTextView = (TextView) convertView.findViewById(R.id.location);
        TextView sexTextView = (TextView) convertView.findViewById(R.id.sex);
        TextView levelTextView = (TextView) convertView.findViewById(R.id.level);
        ImageView icon = (ImageView) convertView.findViewById(R.id.imageView);

        team_nameTextView.setText(item.team_name);
        locationTextView.setText(item.location);
        sexTextView.setText(item.sex);
        levelTextView.setText(item.level);
        icon.setImageResource(R.drawable.android);

        return convertView;
    }
}
