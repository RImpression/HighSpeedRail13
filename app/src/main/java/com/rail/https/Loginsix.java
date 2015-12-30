package com.rail.https;

import android.widget.TextView;

import com.rail.interfaces.InterfaceToUseData;

/**
 * Created by Administrator on 2015.11.30.
 */
public class Loginsix implements InterfaceToUseData {
    private TextView textView;
    public static String url6="https://kyfw.12306.cn/otn/login/userLogin";
    public void loginsix(TextView textView){
        this.textView=textView;
        HttpDoPostUtils.getHttpPost().execute(url6, this, "_json_att=");
    }
    @Override
    public void useData(Object s) {
        textView.setText(s.toString());
        new Loginseven().loginseven(textView);
    }
}
