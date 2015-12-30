package com.rail.https;

import android.content.Context;

import com.rail.interfaces.InterfaceToUseData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015.11.30.
 */
public class BookTicketSecond implements InterfaceToUseData {
    private Context context;
    public BookTicketSecond(Context context){
        this.context=context;
    }
    public void bookTicketsecond(String s){
        HttpDoPostUtils.getHttpPost().execute("https://kyfw.12306.cn/otn/confirmPassenger/initDc",this,s);
    }
    @Override
    public void useData(Object s) {
        System.out.println(s);
        String reg = "src=\"(/otn/dynamicJs/.+?)\"";
        String reg2 = "globalRepeatSubmitToken = \'(.+?)\'";
        String dynamicJs2=null;
        Pattern p = Pattern.compile(reg);
        Pattern p2 = Pattern.compile(reg2);
        Matcher m = p.matcher(s.toString());
        Matcher m2 = p2.matcher(s.toString());
        while(m.find()) {
            if(m.group(1).length()>1)dynamicJs2 = m.group(1);
            	System.out.println(m.group(1));
        }
        String globalRepeatSubmitToken=null;
        while(m2.find()) {
            if(m2.group(1).length()>1)globalRepeatSubmitToken = m2.group(1);
        }

        System.out.println(dynamicJs2+"6666666666666666666"+globalRepeatSubmitToken);
        String[]str =new String[]{dynamicJs2,globalRepeatSubmitToken};
        new BookTicketThird(context).bookticketthird(str);
    }
}
