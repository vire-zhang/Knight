package javabook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThirdSample {

    private static final Logger myLogger = Logger.getLogger("javabook.ThirdSample");

    public static void main(String[] args) throws IOException {

        var handler = new FileHandler();
        handler.setLevel(Level.ALL);
        myLogger.setLevel(Level.ALL);
        myLogger.addHandler(handler);
        myLogger.info(new ThirdSample().getClass() + " 日志记录1");

        String s = "null";
        assert s != null : "s can not be null.";
        Logger.getGlobal().info("日志记录");

        System.out.println(mySqrt(2147395600));
        System.out.println(46340 * 46340);

        Integer[] array = {1,2,3};
        List<Integer> list = Arrays.asList(array);
        array[0] = 5;   // list也相应改变
//        list.add(3);  // 报错
        ArrayList<Integer> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, array);
        array[0] = 9;
        arrayList.add(5);
    }

    public static int mySqrt(int x) {
        return 0;
    }

    public List<String> buildArray(int[] target, int n) {
        List<String> result = new ArrayList<>();
        int i = 1;
        for (int num : target) {
            while (i < num) {
                result.add("Push");
                result.add("Pop");
                ++i;
            }
            result.add("Push");
            ++i;
        }
        return result;
    }

    public int countTriplets(int[] arr) {
        int N = arr.length;
        if (N == 0) return 0;
        int count = 0;
        for (int i = 0; i < N - 1; i++) {
            int a = 0;
            for (int j = i; j < N; j++) {
                a ^= arr[j];
                int b = 0;
                for (int k = j + 1; k < N; k++) {
                    b ^= arr[k];
                    if (a == b) ++count;
                }
            }
        }
        return count;
    }

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        return 0;
    }
}
