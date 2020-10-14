package com.soundsystem.impl;

import com.soundsystem.CompactDisk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.soundsystem.MediaPlayer;

@Component
public class CDPlayer implements MediaPlayer {

    private CompactDisk cd;

    @Autowired
    @Qualifier("blankDisc")
    public void setCd(CompactDisk cd) {
        this.cd = cd;
    }

//    public CDPlayer(CompactDisk cd) {
//        this.cd = cd;
//    }

    @Override
    public void play() {
        cd.play();
    }
}
