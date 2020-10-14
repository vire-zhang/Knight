package com.leetcode.weekyRun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class UndergroundSystemOneClass {

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

    ArrayList<RadingData> radingDatas;

    public UndergroundSystemOneClass() {
        radingDatas = new ArrayList<RadingData>();
    }

    public void checkIn(int id, String stationName, int t) {
        RadingData radingData = new RadingData();
        radingData.setId(id);
        radingData.setStation(stationName);
        radingData.setTime(t);
        radingData.setInOrOut(1);
        radingDatas.add(radingData);
    }

    public void checkOut(int id, String stationName, int t) {
        RadingData radingData = new RadingData();
        radingData.setId(id);
        radingData.setStation(stationName);
        radingData.setTime(t);
        radingData.setInOrOut(0);
        radingDatas.add(radingData);
    }

    public double getAverageTime(String startStation, String endStation) {
        ArrayList<Integer> timeArray = new ArrayList<Integer>();
        Map<Integer,Integer> id_time = new HashMap<>();
        for (RadingData radingData : radingDatas) {
            if (radingData.getStation() == startStation && radingData.isInOrOut() == 1) {
                id_time.put(radingData.getId(), radingData.getTime());
            }
            else if (id_time.get(radingData.getId()) != null) {
                int id = radingData.getId();
                if (radingData.getStation() == endStation) {
                    if (id_time.get(id) != null) {
                        timeArray.add(radingData.getTime() - id_time.get(id));
                    }
                }
                id_time.remove(id);
            }
        }
        int totalTime = 0;
        for (Integer time : timeArray) {
            totalTime += time;
        }
        return (double) totalTime / timeArray.size();
    }

    public static void main(String[] args) {
        UndergroundSystemOneClass undergroundSystem = new UndergroundSystemOneClass();
        undergroundSystem.checkIn(45,"Leyton",3);
        undergroundSystem.checkIn(32,"Paradise",8);
        undergroundSystem.checkIn(27,"Leyton",10);
        undergroundSystem.checkOut(45,"Waterloo",15);
        undergroundSystem.checkOut(27,"Waterloo",20);
        undergroundSystem.checkOut(32,"Cambridge",22);
        double at1 = undergroundSystem.getAverageTime("Paradise","Cambridge");
        System.out.format("Paradise-Cambridge's average time: %f\n", at1);
        double at2 = undergroundSystem.getAverageTime("Leyton","Waterloo");
        System.out.format("Leyton-Waterloo's average time: %f\n", at2);
        undergroundSystem.checkIn(10,"Leyton",24);
        double at3 = undergroundSystem.getAverageTime("Leyton","Waterloo");
        System.out.format("Leyton-Waterloo's average time: %f\n", at3);
        undergroundSystem.checkOut(10,"Waterloo",38);
        double at4 = undergroundSystem.getAverageTime("Leyton","Waterloo");
        System.out.format("Leyton-Waterloo's average time: %f\n", at4);
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
