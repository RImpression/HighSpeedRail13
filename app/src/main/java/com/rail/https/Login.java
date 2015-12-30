package com.rail.https;

import android.content.Context;

import com.rail.interfaces.InterfaceToUseData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ������ on 2015/11/28.
 */
public class Login implements InterfaceToUseData {
    private Context context;
    public Login(Context context){
        this.context=context;
    }
    public static String url1="https://kyfw.12306.cn/otn/login/init";
   public void login(){
       HttpDoPostUtils.getHttpPost().execute(url1, this);
   }
    @Override
    public void useData(Object s) {
        System.out.println(s);
        String dynamicJs=null;
        String reg = "src=\"(/otn/dynamicJs/.+?)\"";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(s.toString());
        while(m.find()) {
            if(m.group(1).length()>1)dynamicJs = m.group(1);
        }
        new Loginsecond(context).loginsecond(dynamicJs);
    }
}
