package com.rail.utils;

import java.util.Comparator;

/**
 * Created by Johon on 2015/12/8.
 */
public class PingyinComparator implements Comparator<String> {
    public int compare(String o1, String o2) {
        //这里主要是用来对ListView里面的数据根据ABCDEFG...来排序
        CharParse charParse = CharParse.getInstance();
        o1 = charParse.getSelling(o1.substring(0,1).toUpperCase());
        o2 = charParse.getSelling(o2.substring(0,1).toUpperCase());
        if (o2.equals("#")) {
            return -1;
        } else if (o1.equals("#")) {
            return 1;
        } else {
            return o1.compareTo(o2);
        }
    }
}
