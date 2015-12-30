package com.rail.https;

import android.content.Context;

import com.rail.interfaces.InterfaceToUseData;

/**
 * Created by on 2015/11/30.
 */
public class BookTicketFourth implements InterfaceToUseData {
    private Context context;
    private String string;
    public BookTicketFourth(Context context){
        this.context=context;
    }
    public void bookticketfour(String s){
        HttpDoPostUtils.getHttpPost().execute("https://kyfw.12306.cn/otn/confirmPassenger/getPassengerDTOs",this,"REPEAT_SUBMIT_TOKEN="+s+"&_json_att=");
        this.string=s;
    }
    @Override
    public void useData(Object s) {
        System.out.println(s.toString());
        System.out.println("DATA"+string);
        new BookTicketFive(context).bookticketfive(string,s.toString());
    }
}
