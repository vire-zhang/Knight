package com.soundsystem;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Aspect
public class TrackCounter {

    private Map<Integer, Integer> trackCounts = new HashMap<Integer, Integer>();

    @Pointcut("execution(* com.soundsystem.CompactDisk.playTrack(int)) " + "&& args(trackNumber)")
    public void trackPlayed(int trackNumber) {}

    @Before("trackPlayed(trackNumber)")
    public void countTrack(int trackNumber) {
        int currentCount = getPlayCount(trackNumber);
        trackCounts.put(trackNumber, currentCount + 1);
    }

    public int getPlayCount(int trackNumber) {
        return trackCounts.containsKey(trackNumber) ? trackCounts.get(trackNumber) : 0;
    }

    public void printTrackCounts() {
        Iterator iter = trackCounts.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry =(Map.Entry) iter.next();
            Integer key = (Integer) entry.getKey();
            Integer val = (Integer) entry.getValue();
            System.out.println("Track-" + key + ": " + val + "次");
        }
    }
}
