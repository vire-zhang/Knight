package javabook.reflect;

import javabook.Employee;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class MethodTableTest {
    public static void main(String[] args) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Method m1 = Employee.class.getMethod("getName");
        Employee e1 = new Employee("alex");
        String n = (String) m1.invoke(e1);

        Class cl = Random.class;
        Constructor cons = cl.getConstructor(long.class);
        Object obj = cons.newInstance(42L);
        System.out.println(new Random(42L).nextInt());
    }
}
