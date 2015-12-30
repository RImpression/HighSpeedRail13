package com.rail.https;

import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.rail.interfaces.InterfaceToUseData;
import com.rail.model.TraverSearchResquestModel;
import com.rail.model.TraverResultModel;
import com.rail.utils.JsonCheCi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//获取车次信息

public class TraverSearch implements InterfaceToUseData {
    private Context context;
    public static List<TraverResultModel> getTraresmodel() {
        return traresmodel;
    }

    static List<TraverResultModel> traresmodel = new ArrayList<TraverResultModel>();
    private static AsyncHttpClient client;
    private static HashMap<String, Object> hs = new HashMap<String, Object>();

    public void HttpToGetData(final Context context,
                              TraverSearchResquestModel resqmodl) {
        HttpDoPostUtils httpDoPostUtils = HttpDoPostUtils.getHttpPost();
        httpDoPostUtils.execute("https://kyfw.12306.cn/otn/leftTicket/queryT?leftTicketDTO.train_date="+resqmodl.getDate()+"&leftTicketDTO.from_station="+resqmodl.getFrom()+"&leftTicketDTO.to_station="+resqmodl.getTo()+"&purpose_codes=ADULT",this);
        this.context=context;
        return;
    }

    @Override
    public void useData(Object s) {
        System.out.println(s);
        if (s != null) {
            JsonCheCi.JsonDataFromString(s.toString(), context);
        } else
            Toast.makeText(context, "未获取到数据", Toast.LENGTH_LONG).show();
        System.out.println(s);
    }
}
