package com.bytedance;

import java.util.Scanner;

public class DecimalToQuinary {

    private final int DIVISOR = 9;

    public String decimalToQuinary(int n) {
        StringBuilder result = new StringBuilder("");
        int divider = Math.abs(n);
        while (divider >= DIVISOR) {
            int remainder = divider % DIVISOR;
            result.insert(0, remainder);
            divider /= DIVISOR;
        }
        result.insert(0, divider);
        if (n < 0) result.insert(0, "-");
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println("十进制：");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        DecimalToQuinary decimalToQuinary = new DecimalToQuinary();
        System.out.println("转换为" + decimalToQuinary.DIVISOR + "进制：" + decimalToQuinary.decimalToQuinary(n));
    }
}
