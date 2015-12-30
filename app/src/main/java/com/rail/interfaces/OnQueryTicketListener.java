package com.rail.interfaces;

/**
 * Created by Administrator on 2015.11.21.
 */
public  interface OnQueryTicketListener {
//    public  abstract void useData(Object s) ;
    public void onSuccess(Object o);
    public void onError(String error);
}

