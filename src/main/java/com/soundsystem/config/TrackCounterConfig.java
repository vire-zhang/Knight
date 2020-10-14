package com.soundsystem.config;

import com.soundsystem.CompactDisk;
import com.soundsystem.TrackCounter;
import com.soundsystem.impl.BlankDisc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAspectJAutoProxy
public class TrackCounterConfig {

    @Bean
    public CompactDisk lover() {
        BlankDisc cd = new BlankDisc();
        cd.setTitle("Lover");
        cd.setArtist("Taylor Swift");
        List<String> tracks = new ArrayList<String>();
        tracks.add("Lover");
        tracks.add("I forgot That You Existed");
        tracks.add("Cruel Summer");
        tracks.add("The Man");
        tracks.add("The Archer");
        tracks.add("London Boy");
        tracks.add("You Need To Clam Calm Down");
        tracks.add("ME!");
        cd.setTracks(tracks);
        return cd;
    }

    @Bean
    public TrackCounter trackCounter() {
        return new TrackCounter();
    }
}
