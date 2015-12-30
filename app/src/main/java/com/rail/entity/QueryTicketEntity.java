package com.rail.entity;

public class QueryTicketEntity {

    //控制信息
    private String controlled_train_message;
    //是否网购
    private String canWebBuy;
    private String start_station_telecode;
    private String end_station_telecode;

    public String getEnd_station_telecode() {
        return end_station_telecode;
    }

    public void setEnd_station_telecode(String end_station_telecode) {
        this.end_station_telecode = end_station_telecode;
    }

    public String getStart_station_telecode() {
        return start_station_telecode;
    }

    public void setStart_station_telecode(String start_station_telecode) {
        this.start_station_telecode = start_station_telecode;
    }

    public String getCanWebBuy() {
        return canWebBuy;
    }

    public void setCanWebBuy(String canWebBuy) {
        this.canWebBuy = canWebBuy;
    }

    public String getStation_train_code() {
        return station_train_code;
    }

    public void setStation_train_code(String station_train_code) {
        this.station_train_code = station_train_code;
    }

    public String getControlled_train_message() {
        return controlled_train_message;
    }

    public void setControlled_train_message(String controlled_train_message) {
        this.controlled_train_message = controlled_train_message;
    }
    private String Data;

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    /*车次*/
    private String station_train_code;
    private String secretStr;
    //buttontext内容
    private String buttonTextInfo;
    //出发站的no序号
    private String from_station_no;
    //到达站的no序号
    private String to_station_no;
//    train_no 编号
    private String train_no;
    /*车次始发站*/
    private String start_station_name;
    /*车次终点站*/
    private String end_station_name;
    /*出发站*/
    private String from_station_name;
    /*到达站*/
    private String to_station_name;
    /*出发时间*/
    private String start_time;
    /*到达时间*/
    private String arrive_time;
    /*车次类型*/
    private String train_class_name;
    /*历时天数*/
    private String day_difference;
    /*总历时时间*/
    private String lishi;
    /*高级软卧：-- 说明无该席位*/
    private String gr_num;
    /*其他*/
    private String qt_num;
    /*软卧*/
    private String rw_num;
    /*软座*/
    private String rz_num;
    /*特等座*/
    private String tz_num;
    /*无座*/
    private String wz_num;
    /*硬卧*/
    private String yw_num;
    /*硬座*/
    private String yz_num;
    /*二等座*/
    private String ze_num;
    /*一等座*/
    private String zy_num;
    //    商务座
    private String swz_num;

    public String getSwz_num() {
        return swz_num;
    }

    public void setSwz_num(String swz_num) {
        this.swz_num = swz_num;
    }

    public String getZy_num() {
        return zy_num;
    }

    public void setZy_num(String zy_num) {
        this.zy_num = zy_num;
    }

    public String getZe_num() {
        return ze_num;
    }

    public void setZe_num(String ze_num) {
        this.ze_num = ze_num;
    }

    public String getYz_num() {
        return yz_num;
    }

    public void setYz_num(String yz_num) {
        this.yz_num = yz_num;
    }

    public String getYw_num() {
        return yw_num;
    }

    public void setYw_num(String yw_num) {
        this.yw_num = yw_num;
    }

    public String getWz_num() {
        return wz_num;
    }

    public void setWz_num(String wz_num) {
        this.wz_num = wz_num;
    }

    public String getTz_num() {
        return tz_num;
    }

    public void setTz_num(String tz_num) {
        this.tz_num = tz_num;
    }

    public String getRz_num() {
        return rz_num;
    }

    public void setRz_num(String rz_num) {
        this.rz_num = rz_num;
    }

    public String getRw_num() {
        return rw_num;
    }

    public void setRw_num(String rw_num) {
        this.rw_num = rw_num;
    }

    public String getQt_num() {
        return qt_num;
    }

    public void setQt_num(String qt_num) {
        this.qt_num = qt_num;
    }

    public String getGr_num() {
        return gr_num;
    }

    public void setGr_num(String gr_num) {
        this.gr_num = gr_num;
    }

    public String getLishi() {
        return lishi;
    }

    public void setLishi(String lishi) {
        this.lishi = lishi;
    }

    public String getDay_difference() {
        return day_difference;
    }

    public void setDay_difference(String day_difference) {
        this.day_difference = day_difference;
    }

    public String getTrain_class_name() {
        return train_class_name;
    }

    public void setTrain_class_name(String train_class_name) {
        this.train_class_name = train_class_name;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(String arrive_time) {
        this.arrive_time = arrive_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getTo_station_name() {
        return to_station_name;
    }

    public void setTo_station_name(String to_station_name) {
        this.to_station_name = to_station_name;
    }

    public String getFrom_station_name() {
        return from_station_name;
    }

    public void setFrom_station_name(String from_station_name) {
        this.from_station_name = from_station_name;
    }

    public String getEnd_station_name() {
        return end_station_name;
    }

    public void setEnd_station_name(String end_station_name) {
        this.end_station_name = end_station_name;
    }

    public String getStart_station_name() {
        return start_station_name;
    }

    public void setStart_station_name(String start_station_name) {
        this.start_station_name = start_station_name;
    }
    public String getSecretStr() {
        return secretStr;
    }

    public void setSecretStr(String secretStr) {
        this.secretStr = secretStr;
    }

    public String getButtonTextInfo() {
        return buttonTextInfo;
    }

    public void setButtonTextInfo(String buttonTextInfo) {
        this.buttonTextInfo = buttonTextInfo;
    }

    public String getTrain_no() {
        return train_no;
    }

    public void setTrain_no(String train_no) {
        this.train_no = train_no;
    }

    public String getTo_station_no() {
        return to_station_no;
    }

    public void setTo_station_no(String to_station_no) {
        this.to_station_no = to_station_no;
    }

    public String getFrom_station_no() {
        return from_station_no;
    }

    public void setFrom_station_no(String from_station_no) {
        this.from_station_no = from_station_no;
    }

    @Override
    public String toString() {
        return "TraverResultModel{" +
                "secretStr='" + secretStr + '\'' +
                ", buttonTextInfo='" + buttonTextInfo + '\'' +
                ", from_station_no='" + from_station_no + '\'' +
                ", to_station_no='" + to_station_no + '\'' +
                ", train_no='" + train_no + '\'' +
                ", start_station_name='" + start_station_name + '\'' +
                ", end_station_name='" + end_station_name + '\'' +
                ", from_station_name='" + from_station_name + '\'' +
                ", to_station_name='" + to_station_name + '\'' +
                ", start_time='" + start_time + '\'' +
                ", arrive_time='" + arrive_time + '\'' +
                ", train_class_name='" + train_class_name + '\'' +
                ", day_difference='" + day_difference + '\'' +
                ", lishi='" + lishi + '\'' +
                ", gr_num='" + gr_num + '\'' +
                ", qt_num='" + qt_num + '\'' +
                ", rw_num='" + rw_num + '\'' +
                ", rz_num='" + rz_num + '\'' +
                ", tz_num='" + tz_num + '\'' +
                ", wz_num='" + wz_num + '\'' +
                ", yw_num='" + yw_num + '\'' +
                ", yz_num='" + yz_num + '\'' +
                ", ze_num='" + ze_num + '\'' +
                ", zy_num='" + zy_num + '\'' +
                ", swz_num='" + swz_num + '\'' +
                '}';
    }

}