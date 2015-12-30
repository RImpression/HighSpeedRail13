package com.rail.https;

import android.content.Context;

import com.rail.interfaces.InterfaceToUseData;
import com.rail.model.StationListResquestModel;
import com.rail.utils.JsonStation;

/**
 * Created by 稻香下 on 2015/11/10.
 */
//获取沿途车站信息
public class TraverStationSearch implements InterfaceToUseData{
    private Context context;
    public void Traverstationsercher(final Context context,StationListResquestModel model) {
        this.context = context;
        System.out.println("https://kyfw.12306.cn/otn/czxx/queryByTrainNo?train_no=" + model.getTrain_no() + "&from_station_telecode=" + model.getFrom_station_telecode() + "&to_station_telecode=" + model.getTo_station_telecode() + "&depart_date=" + model.getDepart_data());
        HttpDoPostUtils.getHttpPost().execute("https://kyfw.12306.cn/otn/czxx/queryByTrainNo?train_no=" + model.getTrain_no() + "&from_station_telecode=" + model.getFrom_station_telecode() + "&to_station_telecode=" + model.getTo_station_telecode() + "&depart_date=" + model.getDepart_data(), this);
    }
    @Override
    public void useData(Object s) {
        System.out.println(s);
        JsonStation.jsonStation(context, s.toString());
    }
}