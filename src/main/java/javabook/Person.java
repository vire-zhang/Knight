package javabook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntConsumer;
import java.util.stream.Stream;

import static java.util.Comparator.*;

public class Person {
    private String name;
    private int age;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // 测试main 构造器引用
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("zhang");
        names.add("wang");
        Stream<Person> stream = names.stream().map(Person::new);
//        List<Person> people = stream.collect(Collectors.toList());
//        Object[] people1 = stream.toArray();
        Person[] people2 = stream.toArray(Person[]::new);
        // 按String比较
        Arrays.sort(people2, Comparator.comparing(Person::getName)
                                .thenComparing(Person::getAge));
        // 处理比较中的空值
        Arrays.sort(people2, Comparator.comparing(Person::getName, Comparator.nullsFirst(naturalOrder())));
        Arrays.sort(people2, Comparator.comparing(Person::getName, Comparator.nullsFirst(reverseOrder())));
        // 按长度比较
        Arrays.sort(people2, Comparator.comparing(Person::getName,
                                (s, t) -> Integer.compare(s.length(), t.length())));
        // 等价
        Arrays.sort(people2, Comparator.comparingInt(p -> p.getName().length()));

        repeat(10, () -> System.out.println("Hello"));

        System.out.println();

        repeat1(10, i -> System.out.println("Countdown: " + (9 - i)));
    }

    /**
     * 函数式接口，处理lambda表达式
     * @param n
     * @param action
     */
    public static void repeat(int n, Runnable action) {
        for (int i = 0; i < n; i++) {
            action.run();
        }
    }

    public static void repeat1(int n, IntConsumer action) {
        for (int i = 0; i < n; i++) {
            action.accept(i);
        }
    }
}
