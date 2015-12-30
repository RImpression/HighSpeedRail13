package com.rail.model;

//请求数据的model
public class TraverSearchResquestModel {
    //时间
    private String date;
    //出发站
    private String from_station;
    //到达站
    private String to_station;

    public String getTrainkind() {
        return trainkind;
    }

    public void setTrainkind(String trainkind) {
        this.trainkind = trainkind;
    }

    //类型
    private String trainkind;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from_station;
    }

    public void setFrom(String from) {
        this.from_station = from;
    }

    public String getTo() {
        return to_station;
    }

    public void setTo(String to) {
        this.to_station = to;
    }
}
