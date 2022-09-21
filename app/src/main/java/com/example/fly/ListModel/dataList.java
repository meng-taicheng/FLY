package com.example.fly.ListModel;

public class dataList {
    public String time;
    public String name;
    public String starttime;
    public String endtime;

    public dataList(String time, String name, String starttime, String endtime, String number, String lowestprice) {
        this.time = time;
        this.name = name;
        this.starttime = starttime;
        this.endtime = endtime;
        this.number = number;
        this.lowestprice = lowestprice;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLowestprice() {
        return lowestprice;
    }

    public void setLowestprice(String lowestprice) {
        this.lowestprice = lowestprice;
    }

    private String number;
    private String lowestprice;
}
