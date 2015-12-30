package com.rail.https;

import android.content.Context;

import com.rail.interfaces.InterfaceToUseData;

/**
 * Created by Administrator on 2015.11.30.
 */
public class BookTicket implements InterfaceToUseData {
    private String s;
    private Context context;
    public BookTicket(Context context){
        this.context=context;
    }
    public void bookticket(String s){
        HttpDoPostUtils.getHttpPost().execute("https://kyfw.12306.cn/otn/leftTicket/submitOrderRequest",this,s);
    }
    @Override
    public void useData(Object s) {
        new BookTicketSecond(context).bookTicketsecond("_json_att=");
    }
}
