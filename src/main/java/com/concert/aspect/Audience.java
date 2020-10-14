package com.concert.aspect;

import org.aspectj.lang.annotation.*;

@Aspect
public class Audience {

    @Pointcut("execution(* com.concert.Performance.perform(..))")
    public void performance() {}

//    @Before("execution(** com.concert.Performance.perform(..))")
    @Before("performance()")
    public void silenceCellPhones() {
        System.out.println("Audience.silenceCellPhones()...");
        System.out.println("Silencing cell phones");
    }

//    @Before("execution(** com.concert.Performance.perform(..))")
    @Before("performance()")
    public void takeSeats() {
        System.out.println("Taking seats");
    }

//    @AfterReturning("execution(** com.concert.Performance.perform(..))")
    @AfterReturning("performance()")
    public void applause() {
        System.out.println("CLAP CLAP CLAP!!!");
    }

//    @AfterThrowing("execution(** com.concert.Performance.perform(..))")
    @AfterThrowing("performance()")
    public void demandRedund() {
        System.out.println("Demanding a refund");
    }
}
