package jvm;

import java.lang.annotation.Native;

public class JVM {

//    uint ageTable::compute_tenuring_threshold(size_t survivor_capacity) {
//        //survivor_capacity是survivor空间的大小
//        size_t desired_survivor_size = (size_t)((((double) survivor_capacity)*TargetSurvivorRatio)/100);
//        size_t total = 0;
//        uint age = 1;
//        while (age < table_size) {
//            total += sizes[age];//sizes数组是每个年龄段对象大小
//            if (total > desired_survivor_size) break;
//            age++;
//        }
//        uint result = age < MaxTenuringThreshold ? age : MaxTenuringThreshold;
//	...
//    }

    Native aNative;
    String s1 = "av";
    String s2 = s1.intern();

    public static void main(String[] args) {
        Integer i1 = 33;
        Integer i2 = 33;
        System.out.println(i1 == i2);
        Integer i3 = 233;
        Integer i4 = 233;
        System.out.println(i3 == i4);
        Double d1 = 1.3;
        Double d2 = 1.3;
        System.out.println(d1 == d2);

        Integer.valueOf(i1);

        System.out.println(JVM.class.getClassLoader());
        System.out.println(JVM.class.getClassLoader().getParent());
        System.out.println(JVM.class.getClassLoader().getParent().getParent());
    }
}
