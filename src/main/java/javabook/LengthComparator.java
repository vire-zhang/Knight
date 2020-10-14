package javabook;

import java.time.LocalDate;
import java.util.*;

public class LengthComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }

    public static void main(String[] args) {
        String[] fr = {"Peter", "Paul", "Mary"};
        Arrays.sort(fr, new LengthComparator());

        Date date = new Date();
        System.out.println(date);
        Date date1 = (Date) date.clone();

        // lambda表达式
        Arrays.sort(fr, (f, s) -> s.length() - f.length());

        ArrayList<Integer> arrayList = new ArrayList<>(5);
        arrayList.removeIf(e -> e == null);
        arrayList.removeIf(Objects::isNull);    //等价

        // 懒计算 供应者
        LocalDate day = LocalDate.of(2000, 1, 1);
        LocalDate hireDay = Objects.requireNonNullElseGet(day, () -> LocalDate.of(1970, 1, 1));

        // 方法引用
        Arrays.sort(fr, String::compareToIgnoreCase);
    }
}