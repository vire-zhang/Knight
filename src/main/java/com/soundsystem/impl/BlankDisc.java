package com.soundsystem.impl;

import com.soundsystem.CompactDisk;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 注入外部值方法 2:解析属性占位符 ${...}
 * 用于组建扫描和自动装配
 * 为了使用占位符，需配置一个 PropertySourcesPlaceholderConfigurer bean
 */
//@Component
//@Primary
public class BlankDisc implements CompactDisk {

    private String title;
    private String artist;
    private List<String> tracks;

    /*SpEL表达式*/
//    public BlankDisc(
//            @Value("#{systemProperties['disc.title']}") String title,
//            @Value("#{systemProperties['disc.artist']}") String artist) {
//        this.title = title;
//        this.artist = artist;
//    }

    /*属性占位符*/
//    public BlankDisc(
//            @Value("${disc.title}") String title,
//            @Value("${disc.artist}") String artist) {
//        this.title = title;
//        this.artist = artist;
//    }

    /**
     * 无参构造函数
     */
    public BlankDisc() {
        super();
    }

    /**
     * 有参构造函数
     * @param title
     * @param artist
     * @param tracks
     */
    public BlankDisc(String title, String artist, List<String> tracks) {
        this.title = title;
        this.artist = artist;
        this.tracks = tracks;
    }

    /**
     * setter and getter
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<String> getTracks() {
        return tracks;
    }

    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }

    //不知道这个bean该放在哪里
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }

    @Override
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
        for (int i = 0; i < tracks.size(); ++i) {
            playTrack(i);
        }
    }

    @Override
    public void playTrack(int trackNumber) {
        System.out.println("Track-" + trackNumber + ": " + tracks.get(trackNumber));
    }
}
