package com.springinaction.knights.config;

import com.springinaction.knights.Knight;
import com.springinaction.knights.Quest;
import com.springinaction.knights.impl.BraveKnight;
import com.springinaction.knights.impl.SlayDragonQuest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KnightConfig {

//    @Bean
//    public Knight knight() {
//        return new BraveKnight(quest());
//    }

    @Bean
    public Quest quest() {
        return new SlayDragonQuest(System.out);
    }
}
