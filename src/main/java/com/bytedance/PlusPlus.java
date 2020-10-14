package com.bytedance;

public class PlusPlus {
    public static void main(String[] args) {
        int a = 0;
        for (int i = 0; i < 99; i++) {
            a = a++;
        }
        int b = 0;
        for (int i = 0; i < 99; i++) {
            b = ++b;
        }
        System.out.println(a);
        System.out.println(b);
        int c = 0;
        int d = 0;
        for (int i = 0; i < 99; i++) {
            c = c++;
            d = c++;
        }
        System.out.println(c);
        System.out.println(d);

        int m = 654;
        int m1 = m >> 2;
        int m2 = m1 & 1;
        System.out.println(m1);
        System.out.println(m2);
        System.out.println();
        printBinaryOfNum(m);
        printBinaryOfNum(-m);
        printBinaryOfNum(-100);
        printBinaryOfNum(-1);
    }

    public static void printBinaryOfNum(int m) {
        for (int i = 31; i >= 0; i--) {
            System.out.print(((m >> i) & 1) + " ");
        }
        System.out.println();
    }
}
