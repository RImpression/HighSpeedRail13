package com.rail.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.rail.activity.R;
import com.rail.utils.CharParse;

import java.util.ArrayList;

public class City_listAdapter extends BaseAdapter {

    public static String[] chars = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#" };
    private ArrayList<String> cityList;
    private ArrayList<String> city_for_char;
    private Context context;
    private ArrayList<City_gridAdapter> citys_gridadapter = new ArrayList<City_gridAdapter>();
    private ArrayList<String> hotCitySet = new ArrayList<>();
    private String dataWhrer;
    private Activity activity;

    public City_listAdapter(ArrayList<String> cityList, Context context,String dataWhrer){
        this.cityList = cityList;
        this.context = context;
        hotCitySet.add("北京");
        hotCitySet.add("北京");
        hotCitySet.add("北京");
        hotCitySet.add("北京");
        hotCitySet.add("北京");
        hotCitySet.add("北京");
        hotCitySet.add("北京");
        hotCitySet.add("北京");
        hotCitySet.add("北京");
        hotCitySet.add("北京");
        hotCitySet.add("北京");
        hotCitySet.add("北京");
        getGridViewAdapters();
        this.dataWhrer = dataWhrer;
        activity = (Activity) context;
    }


    @Override
    public int getCount() {
        return citys_gridadapter.size()+2;
    }

    @Override
    public Object getItem(int position) {
        if(cityList.get(position) != null)
            return cityList.get(position);
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView == null){
            vh = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.city_listview_item,null);

            vh.tv_Char = (TextView) convertView.findViewById(R.id.tv_charater);
            vh.gd_city = (GridView) convertView.findViewById(R.id.gv_city);

            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        if(position == 0){
            vh.tv_Char.setText("最近查询");
            City_gridAdapter adapter = new City_gridAdapter(hotCitySet,context);
            vh.gd_city.setAdapter(adapter);
        } else if (position == 1){
            vh.tv_Char.setText("热门城市");
            City_gridAdapter adapter = new City_gridAdapter(hotCitySet,context);
            vh.gd_city.setAdapter(adapter);
        } else{
            vh.tv_Char.setText(chars[position-2]);
            vh.gd_city.setAdapter(citys_gridadapter.get(position-2));
        }
        vh.gd_city.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view.findViewById(R.id.gv_item_cityname);
                String stationName;
                if(tv == null){
                    stationName = hotCitySet.get(position);
                }else{
                    stationName = tv.getText().toString();
                }
                //Toast.makeText(getApplicationContext(), "你选择的城市是：" + cityname + "，该城市的ID编号是： " + getCityNum(position)
                //, Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                if (dataWhrer.equals("from")) {
                    intent.putExtra("dataFrom", stationName);
                }else if (dataWhrer.equals("to")){
                    intent.putExtra("dataTo",stationName);
                }
                activity.setResult(activity.RESULT_OK, intent);
                activity.finish();
            }
        });

        return convertView;
    }

    private void getGridViewAdapters(){
        city_for_char = new ArrayList<String>();
        City_gridAdapter adapter;
        char temp = 'A';

        //获得汉字转拼音工具类
        CharParse charParse = CharParse.getInstance();

        for (int i = 0; i < cityList.size(); i++) {
            String firstChar = charParse.getSelling(cityList.get(i)).substring(0,1).toUpperCase();
            if(firstChar.equals(String.valueOf(temp))){
                city_for_char.add(cityList.get(i));
            } else{
                adapter = new City_gridAdapter(city_for_char,context);
                citys_gridadapter.add(adapter);
                do {
                    city_for_char = new ArrayList<String>();
                    temp++;
                    if (firstChar.equals(String.valueOf(temp))) {
                        city_for_char.add(cityList.get(i));
                    }else {
                        adapter = new City_gridAdapter(city_for_char,context);
                        citys_gridadapter.add(adapter);
                    }
                }while (!firstChar.equals(String.valueOf(temp)));
            }
        }
        adapter = new City_gridAdapter(city_for_char,context);
        citys_gridadapter.add(adapter);
    }

    //选择改变城市状态
    public class SelectCity implements OnItemClickListener {
        public void onItemClick(AdapterView<?> parent,View view,int position,long id) {
            System.out.println(parent == null);
            String stationName = parent.getItemAtPosition(position).toString();
            //Toast.makeText(getApplicationContext(), "你选择的城市是：" + cityname + "，该城市的ID编号是： " + getCityNum(position)
            //, Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            if (dataWhrer.equals("from")) {
                intent.putExtra("dataFrom", stationName);
            }else if (dataWhrer.equals("to"))
                activity.setResult(activity.RESULT_OK, intent);{
                intent.putExtra("dataTo",stationName);
            }
            activity.finish();
        }

    }

    private class ViewHolder{
        public TextView tv_Char;
        public GridView gd_city;
    }
}
