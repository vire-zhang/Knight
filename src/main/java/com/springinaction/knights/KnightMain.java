package com.springinaction.knights;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KnightMain {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(com.springinaction.knights.config.KnightsRootConfig.class);
//        ClassPathXmlApplicationContext context =
//                new ClassPathXmlApplicationContext("knights.xml");
        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();
        context.close();
    }
}
