package com.rail.https;

import android.content.Context;

import com.rail.interfaces.InterfaceToUseData;

/**
 * Created by ������ on 2015/11/29.
 */
public class Loginsecond implements InterfaceToUseData {
    private Context context;
    public Loginsecond(Context context){
        this.context=context;
    }
    public static String url2="https://kyfw.12306.cn";
    public void loginsecond(String s){
        HttpDoPostUtils.getHttpPost().execute(url2+s, this);
    }
    @Override
    public void useData(Object s) {
        new Loginthree(context).loginthree(s.toString());
//        System.out.println(s.toString());
    }
}
