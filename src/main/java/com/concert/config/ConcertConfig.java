package com.concert.config;

import com.concert.MakerInterface;
import com.concert.aspect.Audience;
import com.concert.aspect.AudienceAround;
import com.concert.aspect.EncoreableIntroducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = {MakerInterface.class})
public class ConcertConfig {

//    @Bean
    public Audience audience() {
        return new Audience();
    }

//    @Bean
    public AudienceAround audienceAround() {
        return new AudienceAround();
    }

    @Bean
    public EncoreableIntroducer encoreableIntroducer() {
        return new EncoreableIntroducer();
    }

//    @Bean
//    public CriticAspect criticAspect () {
//        return new CriticAspect();
//    }
}
