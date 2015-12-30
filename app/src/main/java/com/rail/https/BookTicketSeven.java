package com.rail.https;

import com.rail.interfaces.InterfaceToUseData;

/**
 * Created by on 2015/12/1.
 */
public class BookTicketSeven implements InterfaceToUseData {
    public void bookticketseven(String s){
        System.out.println(s);
        HttpDoPostUtils.getHttpPost().execute("https://kyfw.12306.cn/otn/confirmPassenger/checkOrderInfo",this,s);
    }
    @Override
    public void useData(Object s) {
        System.out.println(s);

    }
}
