package com.rail.model;

import android.os.AsyncTask;

import com.rail.entity.QueryTicketEntity;
import com.rail.https.HttpsRequest;
import com.rail.interfaces.OnQueryTicketListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/13 0013.
 */
public class QueryTicketModel {

    private OnQueryTicketListener listener;
    private HttpsRequest request;
    //暂时存放数据接口
    private  String urlString="https://kyfw.12306.cn/otn/leftTicket/queryT";
 public void query(String from,String to,String date,OnQueryTicketListener listener){
     this.listener=listener;
     String params="leftTicketDTO.train_date="+date+"&leftTicketDTO.from_station="+from+"&leftTicketDTO.to_station="+to+"&purpose_codes=ADULT";
     QueryTicketAsyncTask task = new QueryTicketAsyncTask();
     task.execute(urlString,params,"GET");
 }
class QueryTicketAsyncTask extends AsyncTask<String,Integer,String>{

    @Override
    protected String doInBackground(String... params) {
        try {
            return new HttpsRequest().request(params[0],params[1],params[2]);
        }catch (Exception e){
            return e.toString();
        }
    }
    @Override
    protected void onPostExecute(String s) {
        System.out.println(s);
        if(s!=null&&s.length()>0){
            try {
                List<QueryTicketEntity> entities = jsonToEntity(s);
                listener.onSuccess(entities);
            }catch (Exception e){
                listener.onError(e.toString());
            }
        }else{
            listener.onError(s);
        }

    }
}

    public List<QueryTicketEntity> jsonToEntity(String json)throws JSONException{
        List<QueryTicketEntity> entityList=new ArrayList<QueryTicketEntity>();
        QueryTicketEntity entity;
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            for(int i=0;i<jsonArray.length();i++){
                entity=new QueryTicketEntity();
                JSONObject object=jsonArray.getJSONObject(i).getJSONObject("queryLeftNewDTO");
                entity.setStation_train_code(object.getString("station_train_code"));
                entity.setStart_station_name(object.getString("start_station_name"));
                entity.setEnd_station_name(object.getString("end_station_name"));
                entity.setFrom_station_name(object.getString("from_station_name"));
                entity.setTo_station_name(object.getString("to_station_name"));
                entity.setStart_time(object.getString("start_time"));
                entity.setArrive_time(object.getString("arrive_time"));
                entity.setCanWebBuy(object.getString("canWebBuy"));
                entity.setTrain_no(object.getString("train_no"));
                entity.setDay_difference(object.getString("day_difference"));
                entity.setLishi(object.getString("lishi"));
                entity.setGr_num(object.getString("gr_num"));
                entity.setQt_num(object.getString("qt_num"));
                entity.setRw_num(object.getString("rw_num"));
                entity.setRz_num(object.getString("rz_num"));
                entity.setTz_num(object.getString("tz_num"));
                entity.setWz_num(object.getString("wz_num"));
                entity.setYw_num(object.getString("yw_num"));
                entity.setYz_num(object.getString("yz_num"));
                entity.setZe_num(object.getString("ze_num"));
                entity.setZy_num(object.getString("zy_num"));
                entity.setSwz_num(object.getString("swz_num"));
                entity.setFrom_station_no(object.getString("from_station_no"));
                entity.setTo_station_no(object.getString("to_station_no"));
                entity.setStart_station_telecode(object.getString("start_station_telecode"));
                entity.setCanWebBuy(object.getString("canWebBuy"));
                entity.setEnd_station_telecode(object.getString("end_station_telecode"));
                entity.setSecretStr(jsonArray.getJSONObject(i).getString("secretStr"));
                entity.setButtonTextInfo(jsonArray.getJSONObject(i).getString("buttonTextInfo"));
//                System.out.println(entity.toString());
                entityList.add(entity);
            }
//            System.out.println("resultmodel:" + resultModels.toString());

        } catch (JSONException e) {
            throw new JSONException("json转换成entity失败");
        }
        return entityList;
    }
}
