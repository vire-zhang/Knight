//package com.soundsystem.config;
//
//import com.soundsystem.impl.BlankDisc;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//
///**
// * 注入外部值方法 1：声明属性源并通过 Spring 的 Environment 来检索属性
// */
//@Configuration
////声明属性源
//@PropertySource("classpath:/com/soundsystem/app.properties")
//public class ExpressiveConfig {
//
//    @Autowired
//    Environment env;
//
//    @Bean
//    public BlankDisc disc() {
//        return new BlankDisc(
//                env.getProperty("disc.title1", "Safe & Sound"),
//                env.getProperty("disc.artist", "Taylor Swift"));
//    }
//}
