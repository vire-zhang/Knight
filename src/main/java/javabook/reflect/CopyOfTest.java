package javabook.reflect;

import javabook.Employee;
import javabook.objectAnalyzer.ObjectAnalyzer;

import java.lang.reflect.Array;
import java.util.Arrays;

public class CopyOfTest {

    public static void main(String[] args) throws ReflectiveOperationException{
        int[] a = {1,2,3};
        a = (int[]) goodCopyOf(a, 10);
        System.out.println(Arrays.toString(a));

        Employee[] e = {new Employee("z"), new Employee("m")};
        e = (Employee[]) goodCopyOf(e, 5);
        System.out.println(new ObjectAnalyzer().toString(e));
    }

    public static Object goodCopyOf(Object a, int newLength) {
        Class cl = a.getClass();
        if(!cl.isArray()) return null;
        Class componentType = cl.getComponentType();
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));
        return newArray;
    }
}

