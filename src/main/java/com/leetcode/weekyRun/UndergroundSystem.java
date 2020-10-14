package com.leetcode.weekyRun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UndergroundSystem {

    ArrayList<RadingData> radingDatas;

    public UndergroundSystem() {
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
        return (float) totalTime / timeArray.size();
    }

    public static void main(String[] args) {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
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

        char a = 'a';
        char b = ++a;
        System.out.println(b);
        int i = (int)Math.pow(10, 9) + 7;
        System.out.println(i);
        System.out.println(Long.MAX_VALUE);
    }
}

