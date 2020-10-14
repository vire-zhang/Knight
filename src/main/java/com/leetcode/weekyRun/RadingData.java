package com.leetcode.weekyRun;

/**
 * 乘坐地铁数据结构
 */
public class RadingData {

    /**
     * 乘客id
     */
    private int id;

    /**
     * 站名
     */
    private String station;

    /**
     * 时间
     */
    private int time;

    /**
     * 进出站标志 1:in 0:out
     */
    private int inOrOut;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int isInOrOut() {
        return inOrOut;
    }

    public void setInOrOut(int inOrOut) {
        this.inOrOut = inOrOut;
    }
}
