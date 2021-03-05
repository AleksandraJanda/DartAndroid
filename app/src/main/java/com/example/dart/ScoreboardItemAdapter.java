package com.example.dart;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardItemAdapter extends ArrayAdapter<User> implements View.OnClickListener{
    private ArrayList<User> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView txtName;
        TextView txtSets;
        TextView txtLegs;
        TextView txtScore;
        TextView txtAverage;

    }

    public ScoreboardItemAdapter(ArrayList<User> data, Context context) {
        super(context, R.layout.row, data);
        this.dataSet = data;
        this.mContext = context;
        this.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final User user = getItem(position);

        final ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row, parent, false);
            viewHolder.txtName = convertView.findViewById(R.id.playerName);
            viewHolder.txtSets = convertView.findViewById(R.id.playerSets);
            viewHolder.txtLegs = convertView.findViewById(R.id.playerLegs);
            viewHolder.txtScore = convertView.findViewById(R.id.playerScore);
            viewHolder.txtAverage = convertView.findViewById(R.id.playerAverage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtName.setText(user.getName());
        viewHolder.txtSets.setText(String.valueOf(user.getSets()));
        viewHolder.txtLegs.setText(String.valueOf(user.getLegs()));
        viewHolder.txtScore.setText(String.valueOf(user.getScore()));
        double avgPoints = averagePoints(user.getAvgList());
        viewHolder.txtAverage.setText(String.format("%.2f", avgPoints));
        viewHolder.txtName.setBackgroundColor(Color.TRANSPARENT);

        if(user.isSelected()) {
            viewHolder.txtName.setBackgroundColor(Color.YELLOW);
        }

        return convertView;
    }

    private static double averagePoints(List<Integer> list) {
        double result = 0;
        if(list.size()>0) {
            for(int i: list) {
                result += i;
            }
            result = result / list.size();
        }
        return result;
    }
}
