package com.rail.https;

import android.content.Context;

import com.rail.interfaces.InterfaceToUseData;

/**
 * Created by  on 2015/11/30.
 */
public class BookTicketThird implements InterfaceToUseData {
    private Context context;
    public BookTicketThird(Context context){
        this.context=context;
    }
    private String string;
    public void bookticketthird(String[] s){
        System.out.println(s[0]);
        HttpDoPostUtils.getHttpPost().execute("https://kyfw.12306.cn"+s[0],this);
        string=s[1];
    }
    @Override
    public void useData(Object s) {
//        System.out.println(s.toString());
        new BookTicketFourth(context).bookticketfour(string);
    }
}
