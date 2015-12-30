package com.rail.model;

/**
 * Created by RImpression on 2015/12/22.
 */
public class StationInfo {
    private String staName;
    private String staEname;
    private String staCname;
    private String staJname;

    public StationInfo(String staName, String staEname) {
        this.staName = staName;
        this.staEname = staEname;
    }

    public StationInfo(String staName, String staEname, String staCname, String staJname) {
        this.staName = staName;
        this.staEname = staEname;
        this.staCname = staCname;
        this.staJname = staJname;
    }

    public String getStaName() {
        return staName;
    }

    public void setStaName(String staName) {
        this.staName = staName;
    }

    public String getStaEname() {
        return staEname;
    }

    public void setStaEname(String staEname) {
        this.staEname = staEname;
    }

    public String getStaCname() {
        return staCname;
    }

    public void setStaCname(String staCname) {
        this.staCname = staCname;
    }

    public String getStaJname() {
        return staJname;
    }

    public void setStaJname(String staJname) {
        this.staJname = staJname;
    }
}
