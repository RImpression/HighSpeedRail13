package com.rail.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rail.activity.R;

import java.util.ArrayList;

/**
 * Created by Johon on 2015/12/7.
 */
public class City_gridAdapter extends BaseAdapter{

    private ArrayList<String> citylist;
    private Context context;

    public City_gridAdapter( ArrayList<String> citylist, Context context){
        this.citylist = citylist;
        this.context = context;
    }
    @Override
    public int getCount() {
        return citylist.size();
    }

    @Override
    public Object getItem(int position) {
        return citylist.get(position).toString();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.city_grid_item,null);
            vh = new ViewHolder();
            vh.tv_cityName = (TextView) convertView.findViewById(R.id.gv_item_cityname);

            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv_cityName.setGravity(Gravity.CENTER);
        vh.tv_cityName.setText(citylist.get(position).toString());

        return convertView;
    }

    private class ViewHolder{
        private TextView tv_cityName;
    }
}
