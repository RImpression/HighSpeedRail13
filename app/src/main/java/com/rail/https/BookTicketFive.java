package com.rail.https;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;

import com.rail.activity.CheakActivity;
import com.rail.interfaces.InterfaceToUseData;

/**
 * Created by on 2015/11/30.
 */
public class BookTicketFive implements InterfaceToUseData {
    private Context context;
    private String string;
    private String data;
    public BookTicketFive(Context context){
        this.context=context;
    }
    public static String burl5="https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=passenger&rand=randp";
    public void bookticketfive(String s,String s1){
        HttpDoPostUtils.getHttpPost().execute(burl5,this);
        this.string=s;
        this.data=s1;
    }
    @Override
    public void useData(Object s) {
        Message message=new Message();
        message.obj=s;
        Bundle bundle=new Bundle();
        bundle.putString("string",string);
        bundle.putString("string1",data);
        message.setData(bundle);
        ((CheakActivity) context).handler.sendMessage(message);
    }
}
