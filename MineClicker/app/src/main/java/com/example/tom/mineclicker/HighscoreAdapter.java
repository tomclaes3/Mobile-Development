package com.example.tom.mineclicker;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HighscoreAdapter extends BaseAdapter{
    private Activity activity;
    private ArrayList<HighscoreModel> highscoreModels;
    private static LayoutInflater inflator = null;
    public Resources res;
    HighscoreModel tempValues = null;
    int i = 0;

    public HighscoreAdapter(Activity a, ArrayList<HighscoreModel> d, Resources resLocal) {
        activity = a;
        highscoreModels = d;
        res = resLocal;
        inflator = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (highscoreModels.size() <= 0)
            return 1;
        return highscoreModels.size();

    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView rank;
        public TextView username;
        public TextView floor;
        public TextView clicks;
        public TextView country;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.inflate_highscores, parent, false);
        Log.e("custom", "see you !");

        if (convertView == null) {
            vi = inflator.inflate(R.layout.inflate_highscores, null);
            holder = new ViewHolder();
            holder.rank = vi.findViewById(R.id.rank);
            holder.username = vi.findViewById(R.id.username);
            holder.floor = vi.findViewById(R.id.floor);
            holder.clicks = vi.findViewById(R.id.clicks);
            holder.country = vi.findViewById(R.id.country);
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }

        if (highscoreModels.size() <= 0) {
            holder.rank.setText("NO DATA");
        } else {
            tempValues = null;
            tempValues = highscoreModels.get(position);
            holder.rank.setText(tempValues.getRank());
            holder.username.setText(tempValues.getUsername());
            holder.floor.setText(tempValues.getFloor());
            holder.clicks.setText(tempValues.getClicks());
            holder.country.setText(tempValues.getCountry());
        }

        return vi;
    }
}
