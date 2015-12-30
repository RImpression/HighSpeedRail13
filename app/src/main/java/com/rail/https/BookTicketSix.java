package com.rail.https;

import com.rail.interfaces.InterfaceToUseData;

/**
 * Created by  on 2015/12/1.
 */
public class BookTicketSix implements InterfaceToUseData {
    private String string;
    public void bookticketsix(String s){
        HttpDoPostUtils.getHttpPost().execute("https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn",this,s);
        string=s;
    }
    @Override
    public void useData(Object s) {
        System.out.println(s);
        new BookTicketSeven().bookticketseven("cancel_flag=2&bed_level_order_num=000000000000000000000000000000&passengerTicketStr=O,0,1,��һ��,1,441521199502077714,,N&oldPassengerStr=��һ��,1,441521199502077714,1_&tour_flag=dc&randCode="+string);
    }
}
