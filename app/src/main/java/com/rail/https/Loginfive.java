package com.rail.https;

import android.widget.TextView;

import com.rail.interfaces.InterfaceToUseData;

/**
 * Created by ������ on 2015/11/28.
 */
public class Loginfive implements InterfaceToUseData {
    public static String url5="https://kyfw.12306.cn/otn/login/loginAysnSuggest";
    private TextView textView;
   public void loginfive(TextView textView,String s){
        HttpDoPostUtils.getHttpPost().execute(url5, this, "loginUserDTO.user_name=onesound&userDTO.password=512512&randCode=" + s);
       this.textView=textView;
   }

    @Override
    public void useData(Object s) {
        textView.setText(s.toString());
//        System.out.println(s);
    }
}
