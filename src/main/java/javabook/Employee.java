package javabook;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class Employee {
    private String name;

    public Employee(String n) {
//        name = Objects.requireNonNull(n, "Name cannot be null!");
//        name = n;

//        name = Objects.requireNonNullElse(n, "unknow");

        // 懒计算 供应者
        name = Objects.requireNonNullElseGet(n, () -> new String("unknow"));
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        var e = new Employee("Jane");
        System.out.println(e.getName());

        var e2 = new Employee(null);
        System.out.println(e2.name);

        // 数组默认初始化
        String s = "think";
        System.out.println(s.indexOf("i"));
        int[] i = new int[5];
        String[] ss = new String[3];
        boolean[] b = new boolean[4];
        System.out.println(Arrays.toString(i));
        System.out.println(Arrays.toString(ss));
        System.out.println(Arrays.toString(b));

        // hashCode()方法
        System.out.println("ok".hashCode());
        System.out.println(new StringBuilder("ok").hashCode());

        Point point = new Point(2, 3);
        System.out.println(point.toString());
    }
}
