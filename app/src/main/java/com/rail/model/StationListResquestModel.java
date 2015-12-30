package com.rail.model;

/**
 */
public class StationListResquestModel {
    private String train_no;
    private String from_station_telecode;
    private String to_station_telecode;
    private String depart_data;

    public String getTrain_no() {
        return train_no;
    }

    public void setTrain_no(String train_no) {
        this.train_no = train_no;
    }

    public String getTo_station_telecode() {
        return to_station_telecode;
    }

    public void setTo_station_telecode(String to_station_telecode) {
        this.to_station_telecode = to_station_telecode;
    }

    public String getFrom_station_telecode() {
        return from_station_telecode;
    }

    public void setFrom_station_telecode(String from_station_telecode) {
        this.from_station_telecode = from_station_telecode;
    }

    public String getDepart_data() {
        return depart_data;
    }

    public void setDepart_data(String depart_data) {
        this.depart_data = depart_data;
    }
}
