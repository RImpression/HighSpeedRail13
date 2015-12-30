package com.rail.https;

import android.content.Context;
import android.os.Message;

import com.rail.activity.CheakActivity;
import com.rail.interfaces.InterfaceToUseData;

/**
 * Created by ������ on 2015/11/29.
 */
public class Loginthree implements InterfaceToUseData {
    private Context context;
    public Loginthree(Context context){
        this.context=context;
    }
    public static String url3="https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=login&rand=sjrand";
    public void loginthree(String s){
        HttpDoPostUtils.getHttpPost().execute(url3, this);
    }
    @Override
    public void useData(Object s) {
        Message message=new Message();
        message.obj=s;
        ((CheakActivity)context).handler.sendMessage(message);
    }
}
