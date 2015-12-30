package com.rail.utils;

import android.content.Context;
import android.os.Message;
import android.widget.Toast;

import com.rail.model.TraverResultModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 稻香下 on 2015/11/11.
 */
//Json解析车次信息
public class JsonCheCi {
    public static List<TraverResultModel> JsonDataFromString(String data,Context context) {
        List<TraverResultModel>resultModels=new ArrayList<TraverResultModel>();
        TraverResultModel model;
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(data);
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            for(int i=0;i<jsonArray.length();i++){
                model=new TraverResultModel();
                JSONObject object=jsonArray.getJSONObject(i).getJSONObject("queryLeftNewDTO");
                model.setStation_train_code(object.getString("station_train_code"));
                model.setStart_station_name(object.getString("start_station_name"));
                model.setEnd_station_name(object.getString("end_station_name"));
                model.setFrom_station_name(object.getString("from_station_name"));
                model.setTo_station_name(object.getString("to_station_name"));
                model.setStart_time(object.getString("start_time"));
                model.setArrive_time(object.getString("arrive_time"));
                model.setCanWebBuy(object.getString("canWebBuy"));
                model.setTrain_no(object.getString("train_no"));
                model.setDay_difference(object.getString("day_difference"));
                model.setLishi(object.getString("lishi"));
                model.setGr_num(object.getString("gr_num"));
                model.setQt_num(object.getString("qt_num"));
                model.setRw_num(object.getString("rw_num"));
                model.setRz_num(object.getString("rz_num"));
                model.setTz_num(object.getString("tz_num"));
                model.setWz_num(object.getString("wz_num"));
                model.setYw_num(object.getString("yw_num"));
                model.setYz_num(object.getString("yz_num"));
                model.setZe_num(object.getString("ze_num"));
                model.setZy_num(object.getString("zy_num"));
                model.setSwz_num(object.getString("swz_num"));
                model.setFrom_station_no(object.getString("from_station_no"));
                model.setTo_station_no(object.getString("to_station_no"));
                model.setStart_station_telecode(object.getString("start_station_telecode"));
                model.setCanWebBuy(object.getString("canWebBuy"));
                model.setEnd_station_telecode(object.getString("end_station_telecode"));
                model.setSecretStr(jsonArray.getJSONObject(i).getString("secretStr"));
                model.setButtonTextInfo(jsonArray.getJSONObject(i).getString("buttonTextInfo"));
                System.out.println(model.toString());
                resultModels.add(model);
            }
            System.out.println("resultmodel:" + resultModels.toString());
            //解析完成返回数据
            Message msg =new Message();
            msg.arg1=0;
            msg.obj=resultModels;
//            ((ResultData)context).handler.sendMessage(msg);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(resultModels.size()==0){
            System.out.println(data);
            Toast.makeText(context,data,Toast.LENGTH_LONG).show();
        }
        return resultModels;
    }
}
