package com.soundsystem.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.soundsystem.impl.CDPlayer;

@Configuration
@ComponentScan(basePackageClasses = {CDPlayer.class})
public class CDPlayerConfig {
}
