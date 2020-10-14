package com.soundsystem.config;

import com.soundsystem.impl.CDPlayer;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Import(CDPlayerConfig.class)
@ImportResource("classpath:cdplayer.xml")
public class SoundSystemConfig {
}
