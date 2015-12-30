package com.rail.utils;

import android.content.Context;

import com.rail.model.TraverStation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RImpression on 2015/11/15.
 */
//json解析沿途车站信息
public class JsonStation {
    public static void jsonStation(Context context,String data){
        try {
            JSONObject jsonObject=new JSONObject(data);
            JSONArray jsonArray=jsonObject.getJSONObject("data").getJSONArray("data");
            TraverStation traverStation;
            List<TraverStation> lists=new ArrayList<TraverStation>();
            for(int  i=0;i<jsonArray.length();i++){
                traverStation=new TraverStation();
                JSONObject object=jsonArray.getJSONObject(i);
                traverStation.setTrain_id(object.getString("station_no"));
                traverStation.setLeave_time(object.getString("start_time"));
                traverStation.setArrived_time(object.getString("arrive_time"));
                traverStation.setStation_name(object.getString("station_name"));
                traverStation.setStaytime(object.getString("stopover_time"));
                lists.add(traverStation);
            }
            System.out.println(lists.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
