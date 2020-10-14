package com.yuanfudao;

import com.yuanfudao.DecodeString;
import com.yuanfudao.DecodeStringByStack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/yuanfudao/decodeStringConfig.xml")
//@ContextConfiguration(classes = com.yuanfudao.config.DecodeConfig.class)
public class DecodingStringTest {

    //        String s = "3[a2[ab]]2[bc]";
    String s = "3[a2[ab4[fgh]]]2[bc]16[hy8[ac9[v]]]hyugj90[poi80[iuy100[m]]]";
//        String s = "3[a2[ab4[fgh]]]2[bc]16[hy8[ac9[v]]]hyugj";

    DecodeString decodeString = new DecodeString();
    DecodeStringByStack decodeStringByStack = new DecodeStringByStack();
    DecodeStringRecursive decodeStringRecursive = new DecodeStringRecursive();

    @Test
    public void test1() {
        long startTime = System.nanoTime();
        String out = decodeString.decodeString(s);
        long endTime = System.nanoTime();
        System.out.println(out);
        System.out.println("运行时间：" + (endTime - startTime) + "ns");
        System.out.println(out.equals(decodeStringByStack.decodeString(s)));
        System.out.println(out.equals(decodeStringRecursive.decodeString(s)));
        System.out.println("length: " + out.length());
    }

    @Test
    public void test2() {
        long startTime2 = System.nanoTime();
        String out2 = decodeStringByStack.decodeString(s);
        long endTime2 = System.nanoTime();
        System.out.println(out2);
        System.out.println("运行时间：" + (endTime2 - startTime2) + "ns");
    }

    @Test
    public void test3() {
        long startTime3 = System.nanoTime();
        String out3 = decodeStringRecursive.decodeString(s);
        long endTime3 = System.nanoTime();
        System.out.println(out3);
        System.out.println("运行时间：" + (endTime3 - startTime3) + "ns");
    }
}
