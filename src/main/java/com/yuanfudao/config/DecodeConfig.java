package com.yuanfudao.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {com.yuanfudao.DecodeString.class, com.yuanfudao.config.TimerAspect.class})
public class DecodeConfig {
}
