package javabook;

import java.util.Random;

import static java.lang.Math.sqrt;

public class ConstructorTest {
    public static void main(String[] args) {
        var staff = new Employee1[3];

        staff[0] = new Employee1("Harry", 40000);
        staff[1] = new Employee1(60000);
        staff[2] = new Employee1();

        for (Employee1 e : staff) {
            System.out.println("name=" + e.getName() + ",id=" + e.getId() + ", salary=" + e.getSalary());
        }

        System.out.println(sqrt(5));
    }
}

class Employee1 {
    private static int nextId;

    private int id;
    private String name = "";   // 实例字段初始化
    private double salary;

    // 静态初始化块
    static {
        var generator = new Random();
        nextId = generator.nextInt(10000);  // 员工id随机赋值（0～9999）
    }

    // 对象初始化块
    {
        id = nextId;
        nextId++;
    }

    // 三个重载构造器
    public Employee1 (String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public Employee1 (double s) {
        // 使用this(...)调用另一个构造器
        this("Employee #" + nextId, s);
    }

    public Employee1 () {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}
