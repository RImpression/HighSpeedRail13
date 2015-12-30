package com.rail.model;

public class TraverStation {
    //station的id
    private String train_id;
    //沿途站名
    private String station_name;
    //到达时间
    private String arrived_time;
    //离开时间
    private String leave_time;
    //停留时间
    private String staytime;

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    public String getArrived_time() {
        return arrived_time;
    }

    @Override
    public String toString() {
        return "TraverStation{" +
                "train_id='" + train_id + '\'' +
                ", station_name='" + station_name + '\'' +
                ", arrived_time='" + arrived_time + '\'' +
                ", leave_time='" + leave_time + '\'' +
                ", staytime='" + staytime + '\'' +
                '}';
    }

    public void setArrived_time(String arrived_time) {
        this.arrived_time = arrived_time;
    }

    public String getLeave_time() {
        return leave_time;
    }

    public void setLeave_time(String leave_time) {
        this.leave_time = leave_time;
    }

    public String getStaytime() {
        return staytime;
    }

    public void setStaytime(String staytime) {
        this.staytime = staytime;
    }
}
