package com.rail.https;

import android.widget.TextView;

import com.rail.interfaces.InterfaceToUseData;

/**
 * Created by ������ on 2015/11/29.
 */
public class Loginfour implements InterfaceToUseData {
    private TextView textView;
    private String s1;
    public static String url4="https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn";
    public void loginfour(String s,TextView textView){
        HttpDoPostUtils.getHttpPost().execute(url4, this, "rand=sjrand&randCode=" + s);
        this.s1=s;
        this.textView=textView;
    }
    @Override
    public void useData(Object s) {
//        System.out.println(s);
        textView.setText(s.toString());
        new Loginfive().loginfive(textView,s1);
    }
}
