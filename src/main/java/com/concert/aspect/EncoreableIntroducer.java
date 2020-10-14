package com.concert.aspect;

import com.concert.Encoreable;
import com.concert.impl.DefaultEncoreable;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class EncoreableIntroducer {

    /**
     * 在不入侵类的情况下，为类增添新的方法
     * 通过@DeclareParents注解将Encoreable接口引入到Performance bean中，
     * 1、value属性指定哪种类型的bean要引入Encoreable接口，+表示Performance的所有子类型，而不是Performance本身，
     * 2、defaultImpl属性指定为引入提供实现的类，
     * 3、@DeclareParents所标注的静态属性指明类要引入的接口，在这里，引入的是Encoreable接口。
     * 4、和其他切面一样，需要在Spring中将本类（EncoreableIntroducer）声明为一个bean
     *    当Spring发现一个bean使用@Aspect注解时，Spring就会创建一个代理，然后将调用委托给被代理的bean或者被引入的实现
     */
    @DeclareParents(value = "com.concert.Performance+", defaultImpl = DefaultEncoreable.class)
    public static Encoreable encoreable;
}
