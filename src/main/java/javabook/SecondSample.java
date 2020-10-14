package javabook;

import javabook.objectAnalyzer.ObjectAnalyzer;
import javabook.serviceLoader.impl.CaesarCipher;
import javabook.serviceLoader.Cipher;
import org.junit.Assert;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class SecondSample {

    public static ServiceLoader<Cipher> cipherLoader = ServiceLoader.load(Cipher.class);

    public static Optional<Cipher> getCipher(int minStrength) {
        return cipherLoader.stream()
                .filter(descr -> descr.type() == CaesarCipher.class)
                .findFirst()
                .map(ServiceLoader.Provider::get);
    }

    public static void main(String[] args) throws ReflectiveOperationException {
        System.out.println(1 << 5);
        System.out.println(5 & (1 << 5));

        Optional<Cipher> cipher = cipherLoader.findFirst();

        Class cl = cipher.getClass();
        System.out.println(cl.getName());

        var className = "java.util.Random";
        Class cl1 = Class.forName(className);
        Object obj = cl1.getConstructor().newInstance();
        Random r = new Random();

        char[][] matrix = {{'0','1','1','0','0','1','0','1','0','1'},{'0','0','1','0','1','0','1','0','1','0'},{'1','0','0','0','0','1','0','1','1','0'},{'0','1','1','1','1','1','1','0','1','0'},{'0','0','1','1','1','1','1','1','1','0'},{'1','1','0','1','0','1','1','1','1','0'},{'0','0','0','1','1','0','0','0','1','0'},{'1','1','0','1','1','0','0','1','1','1'},{'0','1','0','1','1','0','1','0','1','1'}};
        Solution solution = new Solution();
        System.out.println(solution.maximalSquare(matrix));

        System.out.println();
        System.out.println(new ObjectAnalyzer().toString(matrix));
        System.out.println();
        System.out.println(new ObjectAnalyzer().toString(solution));
        System.out.println(new ObjectAnalyzer().toString(new Employee1(1)));

//        Cipher cipher = getCipher(1);
        var message = "Meet me at the toga party.";
        byte[] bytes = cipher.get().encrypt(message.getBytes(), new byte[] { 3 });
        var encrypted = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(encrypted);

        var t = new Throwable();
        var out = new StringWriter();
        t.printStackTrace(new PrintWriter(out));
        String description = out.toString();
        System.out.println(description);


    }
}

class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int max = Math.min(m, n);
        int curr = 0;
        for (int i = 0; i < m; ++i) {
            max = Math.min(m - i, n);
            if (curr >= max) break;
            for (int j = 0; j < n; ++j) {
                if (curr >= n - j) break;
                if (matrix[i][j] == '1') {
                    int count = 1;
                    while(i + count < m && j + count < n) {
                        boolean breakFlag = false;
                        for (int s = i; s <= i + count; ++s) {
                            if (matrix[s][j + count] != '1') {
                                breakFlag = true;
                                break;
                            }
                        }
                        if (breakFlag) break;
                        for (int t = j; t < j +count; ++t) {
                            if (matrix[i + count][t] != '1') {
                                breakFlag = true;
                                break;
                            }
                        }
                        if (breakFlag) break;
                        ++count;
                    }
                    if (count > curr) curr = count;
                }
            }
        }
        return curr * curr;
    }
}

